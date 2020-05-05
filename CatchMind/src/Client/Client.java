package Client;

import Code.CodeList;
import java.awt.event.*;
import java.io.*;
import java.net.*;

import javax.swing.JOptionPane;

public class Client implements CodeList, ActionListener, MouseListener, MouseMotionListener {

	DataOutputStream dout;
	GameFrame gf;
	int ServerPort;
	LoginFrame If;
	RoomCreateFrame rcf;
	RoomListFrame rlf;
	Socket serverSocket;
	String nickname;
	String ServerIp;
	int ox, oy, x, y;

	Client(String ip, int port) {
		ServerIp = ip;
		ServerPort = port;

		try {
			serverSocket = new Socket(ServerIp, ServerPort);
			dout = new DataOutputStream(serverSocket.getOutputStream());
		} catch (Exception e) {
			System.exit(0);
		}
		init();
		start();
	}

	public void start() {
		If.setVisible(true);
		try {
			new Thread(new receiver(new DataInputStream(serverSocket.getInputStream()))).start();
		} catch (IOException e) {
		}
	}

	public void init() {
		gf = new GameFrame(this);
		If = new LoginFrame(this);
		rcf = new RoomCreateFrame(this);
		rlf = new RoomListFrame(this);
		gf.setSendButtonListener(this);
	}

	public void Sender(String msg) {
		try {
			dout.writeUTF(msg);
		} catch (Exception e) {

		}
	}

	class receiver implements Runnable {
		DataInputStream din;

		receiver(DataInputStream s) {
			try {
				din = s;
			} catch (Exception e) {

			}
		}

		public void update(String s) {
			String[] g = s.split("#");
			try {
				switch (Integer.parseInt(g[1])) {
				case Login:
					nickname = If.getID();
					rlf.setVisible(true);
					If.setVisible(false);
					break;
				case UserListAdd:
					gf.UserListAdd(g[0]);
					break;
				case UserListRemove:
					gf.UserListRemove(g[0]);
					break;
				case RoomListAdd:
					rlf.RoomListUpdate(g[0]);
					break;
				case RoomListRemove:
					rlf.RoomListRemove(g[0]);
					break;
				case Chat:
					gf.setMsg(g[0]);
					break;
				case Draw:
					gf.Drawing(g[0]);
					break;
				case Roomin:
					gf.setVisible(true);
					rlf.setVisible(false);
					rcf.setTextClear();
					rcf.setVisible(false);
					break;
				case RoomClear : gf.RoomClear(); break;
				case TokenChange : gf.isTurn = Boolean.valueOf(g[0]);
				gf.setListener();
				break;
				case EndMouse : gf.sg.DrawShape(); break;
				}
			} catch (Exception e) {

			}
		}
		
		public void run() {
			try {
				while(din != null) {
					update(din.readUTF());
				}
			}catch (Exception e) {
				try {
					serverSocket.close();
					din.close();
				} catch (IOException e1) {
				
				}
			}
		}
	}

	public static void main(String[] args) {
		new Client("localhost", 9900);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand() == If.getloginbuttonactioncommand()) {
			Sender(If.getID() + "#" + Login);
		} else if (e.getActionCommand() == rlf.getroomcreatebuttonactioncommend()) {
			rcf.setVisible(true);
		} else if (e.getActionCommand() == rlf.getGoRoomButtonactioncommend()) {
			Sender(rlf.getRoomName() + "#" + Roomin);
		} else if(e.getActionCommand() == rcf.getcreatebuttonactioncommend()) {
			Sender(rcf.getRoomName() + "#" + CreRoom);
		} else if (e.getActionCommand() == gf.getsendbuttonactioncommend()) {
			Sender(nickname + ": " + gf.getMsg() + "#" + Chat);
			gf.setTextClear();
		} else if (e.getActionCommand() == gf.getexitbuttonacctioncommend()) {
			Sender(" #" + ExitRoom);
			rlf.setVisible(true);
			gf.setVisible(false);
		}
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		x = e.getX(); y = e.getY();
		Sender (String.valueOf(ox) + "/" + oy + "/" + x + "/" + y + "/" + "#" + Draw);
		ox = x; oy = y;
	}

	@Override
	public void mousePressed(MouseEvent e) {
		ox = e.getX(); oy = e.getY();
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		Sender(" #" + EndMouse);
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		
	}
	
	
}
