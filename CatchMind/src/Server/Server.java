package Server;

import java.io.*;
import java.net.*;
import java.util.*;
import Code.CodeList;


public class Server extends Thread implements CodeList {
	ServerSocket ss;
	HashMap<String, UserInfo> UI = new HashMap<String, UserInfo>(); // 멀티 쓰레드 사용 시 동기화 빠름
	Vector<RoomInfo> RI = new Vector<RoomInfo>();

	Server() {
		Collections.synchronizedMap(UI);
		try {
			ss = new ServerSocket(9900);
			System.out.println("서버연결완료");
		} catch (IOException e) {
		}
		start();
	}

	public void start() {
		while (true) {
			try {
				Thread th = new Thread(new UserInfo(ss.accept()));
				th.start();
			} catch (IOException e) {
			}
		}
	}

	public RoomInfo getRoomInfo(String s) {
		int size = RI.size();
		String roomname = s;
		RoomInfo reginfo = null;
		for (int i = 0; i < size; i++) {
			reginfo = RI.get(i);
			if (reginfo.getRoomName() == roomname) {
				break;
			}
		}
		return reginfo;
	}

	public String FRoomUpdate(int i) {
		return RI.get(i).getRoomInfo();
	}

	public void sendtoall(String s) {
		String msg = s;
		Iterator it = UI.keySet().iterator();
		while (it.hasNext()) {
			DataOutputStream reg = UI.get(it.next()).dout;
			try {
				reg.writeUTF(msg);
			} catch (Exception e) {
			}
		}
	}

	class UserInfo implements Runnable {
		Socket usersocket;
		DataOutputStream dout;
		String ID;
		RoomInfo Room;

		UserInfo(Socket s) {
			try {
				usersocket = s;
				dout = new DataOutputStream(s.getOutputStream());
			} catch (Exception e) {
			}
		}

		@Override
		public void run() {
			DataInputStream din;
			try {
				din = new DataInputStream(usersocket.getInputStream());
				while (din != null) {
					String h = din.readUTF();
					processing(h);
				}
			} catch (IOException e) {
				if (Room.getRoomSize() == 0) {
					sendtoall(Room.getRoomName() + "#" + RoomListRemove);
					RI.remove(Room);
				} else {
					Room.broadcast(ID + "#" + UserListRemove);
					Room.remove(ID);
					if (Room.host == ID)
						Room.TokenChange(ID);
				}
			} finally {
				UI.remove(ID);
				usersocket = null;
				dout = null;
				Room = null;
			}
		}

		public void requestmsg(String s) {
			String msg = s;
			try {
				dout.writeUTF(msg);
			} catch (Exception e) {
			}
		}

		public void processing(String s) {
			String msg = s;
			String[] g = msg.split("#");

			switch (Integer.valueOf(g[1])) {
			case Login:
				ID = g[0];
				UI.put(ID, this);
				requestmsg(" #" + Login);
				if (RI.size() != 0) {
					for (int i = 0; i < RI.size(); i++) {
						requestmsg(FRoomUpdate(i) + "#" + RoomListAdd);
					}
				}
				break;
			case CreRoom:
				Room = new RoomInfo(g[0], ID);
				RI.add(Room);
				Room.setUserInfo(this);
				requestmsg(" #" + Roomin);
				requestmsg("true#" + TokenChange);
				sendtoall(Room.getRoomInfo() + "#" + RoomListAdd);
				Room.broadcast(ID + "#" + UserListAdd);
				break;
			case Roomin:
				boolean t = true;
				Room = getRoomInfo(g[0]);
				try {
					for (int i = 0; i < Room.getRoomSize(); i++) {
						requestmsg(Room.RoomUser.get(i).ID + "#" + UserListAdd);
					}
				} catch (NullPointerException e) {
					requestmsg(g[0] + "#" + UserListRemove);
					t = false;
				}
				if (t) {
					Room.setUserInfo(this);
					requestmsg(" #" + Roomin);
					sendtoall(Room.getRoomInfo() + "#" + RoomListAdd);
					Room.broadcast(ID + "#" + UserListAdd);
				}
				break;
			case ExitRoom:
				Room.TokenChange(ID);
				requestmsg("false" + "#" + TokenChange);
				Room.remove(ID);
				if (Room.getRoomSize() == 0) {
					sendtoall(Room.getRoomName() + "#" + RoomListRemove);
					requestmsg(" #" + RoomClear);
					RI.remove(Room);
				} else {
					sendtoall(Room.getRoomInfo() + "#" + RoomListAdd);
					requestmsg(" #" + RoomClear);
					Room.broadcast(ID + "#" + UserListRemove);
				}
				break;
			case TokenChange:
				Room.TokenChange(ID);
				requestmsg("false" + "#" + TokenChange);
				break;
			case Chat:
				Room.broadcast(msg);
				break;
			case Draw:
				Room.broadcast(msg);
				break;
			default:
				Room.broadcast(msg);
				break;
			}
		}
	}

	class RoomInfo {
		private Vector<UserInfo> RoomUser = new Vector<UserInfo>();
		private String roomname;
		private String host;
		private int roomsize;

		RoomInfo(String s, String host) {
			roomname = s;
			this.host = host;
		}

		public void setUserInfo(UserInfo i) {
			RoomUser.add(i);
			roomsize = RoomUser.size();
		}

		public void TokenChange(String name) {
			for (int i = 0; i < RoomUser.size(); i++) {
				if (RoomUser.get(i).ID != name) {
					RoomUser.get(i).requestmsg("true" + "#" + TokenChange);
					host = RoomUser.get(i).ID;
					break;
				}
			}
		}

		public String getRoomName() {
			return roomname;
		}

		public int getRoomSize() {
			return roomsize;
		}

		public String getRoomInfo() {
			return roomname + "/" + host + "/" + roomsize;
		}

		public void remove(String s) {
			String removename = s;
			for (int i = 0; i < roomsize; i++) {
				if (removename.equals(RoomUser.elementAt(i).ID)) {
					RoomUser.remove(i);
					break;
				}
			}
			roomsize = RoomUser.size();
		}

		public void broadcast(String s) {
			String msg = s;
			for (int i = 0; i < roomsize; i++) {
				try {
					RoomUser.get(i).dout.writeUTF(msg);
				} catch (IOException e) {
				}
			}
		}
	}

	public static void main(String args[]) {
		new Server();
	}
}