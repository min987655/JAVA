package action;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import info.Rooms;
import info.Users;

public class MainHandler extends Thread {

	private BufferedReader br;
	private PrintWriter pw;
	private Socket socket;
	private Connection conn;
	private PreparedStatement pstmt;
	private Users user;

	private ArrayList<MainHandler> allUserList; // 전체 사용자
	private ArrayList<MainHandler> waitUserList; // 대기실 사용자
	private ArrayList<Rooms> roomTotalList; // 전체 방 리스트

	private Rooms userRoom; // 사용자가 있는 방

	// 소켓, 전체사용자, 대기방, 방리스트, JDBC
	public MainHandler(Socket socket, ArrayList<MainHandler> allUserList, ArrayList<MainHandler> waitUserList,
			ArrayList<Rooms> roomTotalList, Connection conn) {
		this.user = new Users();
		this.userRoom = new Rooms();
		this.socket = socket;
		this.allUserList = allUserList;
		this.waitUserList = waitUserList;
		this.roomTotalList = roomTotalList;
		this.conn = conn;

		try {
			br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		// 데이터 입력받음 데이터 파싱 -> 결과 실행해줘야 함
		try {
			String[] line = null;
			while (true) {
				line = br.readLine().split("\\|");

				if (line == null) {
					break;
				}
				if (line[0].compareTo(Protocol.REGISTER) == 0) { // 회원가입
					String users[] = line[1].split("%");

					String sql = "Insert into users values(num.nextval,?,?)";
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, users[0]);
					pstmt.setString(2, users[1]);
					pstmt.setString(3, users[2]);
					pstmt.setString(4, users[3]);
					pstmt.setString(5, users[4]);
					pstmt.setString(6, users[5]);

					int su = pstmt.executeUpdate(); // 항상 몇개를 실행(CRUD)한지 갯수를 return
					System.out.println(su + "회원가입[DB]");

				} else if (line[0].compareTo(Protocol.IDSEARCHCHECK) == 0) { // 회원가입 ID 중복체크
					System.out.println(line[0] + "/" + line[1]);
					String sql = "SELECT * FROM users WHERE idName = '" + line[1] + "'";
					pstmt = conn.prepareStatement(sql);
					ResultSet rs = pstmt.executeQuery(sql);
					String name = null;
					int count = 0;
					while (rs.next()) {
						name = rs.getString("idName");
						if (name.compareTo(line[1]) == 0) {
							count++;
						}
					}
					System.out.println(count);
					if (count == 0) { // 중복 안되어 가입가능
						pw.println(Protocol.IDSEARCHCHECK_OK + "|" + "MESSAGE");
						pw.flush();
					} else {
						pw.println(Protocol.IDSEARCHCHECK_NO + "|" + "MESSAGE");
						pw.flush();
					}
				} else if (line[0].compareTo(Protocol.ENTERLOGIN) == 0) { // 로그인
					boolean con = true; // 기존에 로그인 되어있는지 확인용 변수
					System.out.println("login");
					String[] users = line[1].split("%");

					System.out.println(users[0] + "/" + users[1]);

					for (int i = 0; i < waitUserList.size(); i++) {
						if ((waitUserList.get(i).user.getIdName()).compareTo(users[0]) == 0) {
							con = false;
						}
					}
					if (con) {
						String sql = "SELECT * FROM users WHERE (idName = '" + users[0] + "' and password = '" + users[1]
								+ "')";

						pstmt = conn.prepareStatement(sql);
						ResultSet rs = pstmt.executeQuery(sql);
						int count = 0;

						while (rs.next()) {
							user.setPryNumber(rs.getInt("priNumber"));
							user.setIdName(rs.getString("idNAME"));
							user.setPassword(rs.getString("PASSWORD"));

							count++;
						}
						System.out.println(count);

						if (count == 0) { // ID, PW 틀리면
							pw.println(Protocol.ENTERLOGIN_NO + "|" + "로그인에 실패하였습니다");
							pw.flush();

							user.setPryNumber(0);
							user.setIdName("");
							user.setPassword("");
						} else { // 로그인 되었을 때
							waitUserList.add(this); // 대기방 인원수 추가
							String userline = "";
							for (int i = 0; i < waitUserList.size(); i++) {
								userline = userline + (waitUserList.get(i).user.getIdName() + ":");
							}
							for (int i = 0; i < waitUserList.size(); i++) {
								waitUserList.get(i).pw.println(
										Protocol.ENTERLOGIN_OK + "|" + user.getIdName() + "|님이 입정하였습니다.|" + userline);
								waitUserList.get(i).pw.flush();
							}
							System.out.println("[대기방 인원 수] :" + waitUserList.size());

							System.out.println("[Room 정보]");
							for (Rooms room : roomTotalList) {
								System.out.println(room.toString() + "현재방 인원 수 :" + room.roomInUserList.size());
							}
							System.out.println("[전체 Room 수]" + roomTotalList.size());

							// roomTotalList 전체 정보를 Message로 만들어 보냄
							String roomListMessage = "";

							for (int i = 0; i < roomTotalList.size(); i++) {
								roomListMessage = roomListMessage + (roomTotalList.get(i).getRID() + "%"
										+ roomTotalList.get(i).getTitle() + "%" + roomTotalList.get(i).getUserCount()
										+ "%" + roomTotalList.get(i).getMasterName() + "%"
										+ roomTotalList.get(i).getCondtionP() + "%"
										+ roomTotalList.get(i).roomInUserList.size() + "-");
							}
							System.out.println(roomListMessage);

							if (roomListMessage.length() != 0) {
								for (int i = 0; i < waitUserList.size(); i++) {
									waitUserList.get(i).pw.println(Protocol.ROOMMAKE_OK + "|" + roomListMessage);
									waitUserList.get(i).pw.flush();
								}
							}
						}
						System.out.println(user.toString());
					} else {
						pw.println(Protocol.ENTERLOGIN_NO + "|" + "이미 로그인중입니다.");
						pw.flush();
					}
				} else if (line[0].compareTo(Protocol.EXITWAITROOM) == 0) { // 대기실방에서 로그인페이지 logout
					String thisName = waitUserList.get(waitUserList.indexOf(this)).user.getIdName(); // - 여기 다시하기

					waitUserList.remove(this);
					System.out.println("[대기방 인원 수] :" + waitUserList.size());

					String userline = "";
					for (int i = 0; i < waitUserList.size(); i++) {
						userline = userline + (waitUserList.get(i).user.getIdName() + ":");
					}
					System.out.println("대기자 인원 :" + userline);
					for (int i = 0; i < waitUserList.size(); i++) {
						waitUserList.get(i).pw.println( // 대기실에 메세지 전송
								Protocol.EXITWAITROOM + "|" + thisName + "|님이 퇴장하였습니다.|" + userline);
						waitUserList.get(i).pw.flush();
					}
					user.setPryNumber(0);
					user.setIdName("");
					user.setPassword("");

				} else if (line[0].compareTo(Protocol.SENDMESSAGE) == 0) { // 대기실에서 메세지 보내기
					for (int i = 0; i < waitUserList.size(); i++) {
						waitUserList.get(i).pw
								.println(Protocol.SENDMASSAGE_ACK + "|" + user.getIdName() + "|" + line[1]);
						waitUserList.get(i).pw.flush();
					}
				} else if (line[0].compareTo(Protocol.ROOMMAKE) == 0) { // 방만들기
					String[] users = line[1].split("%");

					String sql = "";
					Rooms tempRoom = new Rooms();
					sql = "INSERT INTO rooms values(num.nextval,?,?,?,?)";
					pstmt = conn.prepareStatement(sql);

					pstmt.setString(1, users[0]); // title
					pstmt.setString(2, users[1]); // count
					pstmt.setString(3, user.getIdName()); // 방장 이름
					pstmt.setString(4, users[2]); // condition

					tempRoom.setTitle(users[0]);
					tempRoom.setUserCount(users[1]);
					tempRoom.setMasterName(user.getIdName());
					tempRoom.setCondtionP(Integer.parseInt(users[2]));

					sql = "SELECT * FROM rooms WHERE title = '" + users[0] + "' and users = '" + users[1]
							+ "' and MasterName = '" + user.getIdName() + "'";

					int su = pstmt.executeUpdate(); // 항상 몇개를 실행(CRUD)한지 갯수를 return
					System.out.println(su + "Room 만듬(DB)");

					pstmt = conn.prepareStatement(sql);
					ResultSet rs = pstmt.executeQuery(sql);

					int count = 0;
					int priNumber = 0;

					while (rs.next()) {
						count++;
						priNumber = rs.getInt("RID");
					}

					if (count != 0) {
						tempRoom.setRID(priNumber);
						tempRoom.roomInUserList.add(this);
						roomTotalList.add(tempRoom);
						userRoom = tempRoom;
					}

					System.out.println("[Room 정보]");
					for (Rooms rooms : roomTotalList) {
						System.out.println(rooms.toString() + "현재방 인원 수 :" + rooms.roomInUserList.size());
					}
					System.out.println("[전체 Room 수]" + roomTotalList.size());

					// roomTotalList 전체 저보를 Message로 만들어 보냄
					String roomListMessage = "";

					for (int i = 0; i < roomTotalList.size(); i++) {
						roomListMessage = roomListMessage + (roomTotalList.get(i).getRID() + "%"
								+ roomTotalList.get(i).getTitle() + "%" + roomTotalList.get(i).getUserCount() + "%"
								+ roomTotalList.get(i).getMasterName() + "%" + roomTotalList.get(i).getCondtionP() + "%"
								+ roomTotalList.get(i).roomInUserList.size() + "-");
					}
					System.out.println(roomListMessage);

					for (int i = 0; i < waitUserList.size(); i++) {
						if (waitUserList.get(i).user.getIdName().compareTo(tempRoom.getMasterName()) == 0) { // 방만든
																												// 사람에게는
																												// 바로
																												// 채팅화면으로
							waitUserList.get(i).pw.println(Protocol.ROOMMAKE_OK1 + "|" + tempRoom.getMasterName());
							waitUserList.get(i).pw.flush();
						} else { // 다른 대기방 사람들에게는 대기방만 새로고침
							waitUserList.get(i).pw.println(Protocol.ROOMMAKE_OK + "|" + roomListMessage);
							waitUserList.get(i).pw.flush();
						}
					}
					waitUserList.remove(this); // 대기방에서 나가고
					System.out.println("대기방 인원수 누군가 방만들었을 때" + waitUserList.size());

					String userline = "";
					for (int i = 0; i < waitUserList.size(); i++) {
						userline = userline + (waitUserList.get(i).user.getIdName() + ":");
					}
					for (int i = 0; i < waitUserList.size(); i++) {
						waitUserList.get(i).pw.println(Protocol.ENTERLOGIN_OK + "|" + tempRoom.getMasterName() + "|님이"
								+ tempRoom.getRID() + "번 방을 만들었습니다.|" + userline);
						waitUserList.get(i).pw.flush();
					}
				} else if (line[0].compareTo(Protocol.ENTERROOM) == 0) { // 방 입장 버튼
					String thisName = waitUserList.get(waitUserList.indexOf(this)).user.getIdName();

					int roomId = Integer.parseInt(line[1]); // 룸ID

					int index = 0;
					for (int i = 0; i < roomTotalList.size(); i++) {
						if (roomTotalList.get(i).getRID() == roomId) {
							roomTotalList.get(i).roomInUserList.add(this); // 방에 유저 넣음
							userRoom = roomTotalList.get(i);
							index = i;
						}
					}
					String roomListMessage = "";
					for (int i = 0; i < roomTotalList.size(); i++) {
						roomListMessage = roomListMessage + (roomTotalList.get(i).getRID() + "%"
								+ roomTotalList.get(i).getTitle() + "%" + roomTotalList.get(i).getUserCount() + "%"
								+ roomTotalList.get(i).getMasterName() + "%" + roomTotalList.get(i).getCondtionP() + "%"
								+ roomTotalList.get(i).roomInUserList.size() + "-");
					}
					System.out.println(roomListMessage);
					System.out.println(thisName);

					String roomMember = ""; // 방에 유저를 넣어 줌. 방에 추가로 입장한 유저를 가지고 와야하고, 몇번방인지 찾아야 함

					for (int i = 0; i < roomTotalList.get(index).roomInUserList.size(); i++) { // 룸안에 유저의 수 만큼
						roomMember = roomMember
								+ (roomTotalList.get(index).roomInUserList.get(i).user.getIdName() + "%");
					}
					for (int i = 0; i < waitUserList.size(); i++) {
						if (waitUserList.get(i).user.getIdName().compareTo(thisName) == 0) { // 방 들어가는 사람에게는 바로 채팅화면으로
							waitUserList.get(i).pw.println(Protocol.ENTERROOM_OK1 + "|" + "message");
							waitUserList.get(i).pw.flush();
						} else { // 다른 대기방 사람들에게는 대기방만 새로고침
							waitUserList.get(i).pw.println(Protocol.ROOMMAKE_OK + "|" + roomListMessage); // 룸 리스트 새로고침
							waitUserList.get(i).pw.flush();
						}
					}
					waitUserList.remove(this); // 대기방에서 나가고
					System.out.println("방입장 동작 부분 --> [대기실 인원 수 ]" + waitUserList.size());

					String userline = ""; // 채팅창에

					for (int i = 0; i < waitUserList.size(); i++) {
						userline = userline + (waitUserList.get(i).user.getIdName() + ":" + "");
					}
					for (int i = 0; i < waitUserList.size(); i++) {
						waitUserList.get(i).pw.println(
								Protocol.EXITWAITROOM + "|" + thisName + "|님이 " + roomId + "방에 입장하였습니다." + userline); // 대기방에
																														// 메세지
																														// 전송
						waitUserList.get(i).pw.flush();
					}
				} else if (line[0].compareTo(Protocol.EXITCHATTINGROOM) == 0) { // 방 나가기 버튼
					int roomIndex = 0;
					boolean con = true;

					for (int i = 0; i < roomTotalList.size(); i++) {
						if (roomTotalList.get(i).getRID() == userRoom.getRID()) {
							if (roomTotalList.get(i).roomInUserList.size() == 1) { // 채팅방 마지막 1명일 때
								System.out.println("나올 때 내가 마지막일 때");
								roomTotalList.remove(userRoom);
								userRoom = new Rooms();
								con = false;
							} else { // 최소 2명일 때
								System.out.println("나올 때 내가 마지막이 아닐 때");
								roomTotalList.get(i).roomInUserList.remove(this); // 방에 유저 빼고
								userRoom = new Rooms(); // 현재 룸 비워 줌
								roomIndex = i;
							}
						}
					}

					if (con) { // 남아있는 방에 최소 2명 이상일 때
						String roomMember = ""; // 방에 유저를 넣어 줌. 방에 추가로 입장한 유저를 가지고 와야하고, 몇번방인지 찾아야 함

						for (int i = 0; i < roomTotalList.get(roomIndex).roomInUserList.size(); i++) { // 룸안의 유저 수만큼
							roomMember = roomMember
									+ (roomTotalList.get(roomIndex).roomInUserList.get(i).user.getIdName() + "%");
						}
						System.out.println("특정방에 사람 수 : " + roomTotalList.get(roomIndex).roomInUserList.size());
						System.out.println(roomMember);
						for (int i = 0; i < roomTotalList.get(roomIndex).roomInUserList.size(); i++) {
							roomTotalList.get(roomIndex).roomInUserList.get(i).pw
									.println(Protocol.ENTERROOM_USERLISTSEND + "|" + roomMember + "|" + user.getIdName()
											+ "님이 퇴장하셨습니다.");
							roomTotalList.get(roomIndex).roomInUserList.get(i).pw.flush();
						}
					}
					String roomListMessage = "";

					System.out.println(roomListMessage);

					waitUserList.add(this); // 대기방에서 추가
					if (roomTotalList.size() > 0) {
						roomListMessage = "";
						for (int i = 0; i < roomTotalList.size(); i++) {
							roomListMessage = roomListMessage + (roomTotalList.get(i).getRID() + "%"
									+ roomTotalList.get(i).getTitle() + "%" + roomTotalList.get(i).getUserCount() + "%"
									+ roomTotalList.get(i).getMasterName() + "%" + roomTotalList.get(i).getCondtionP()
									+ "%" + roomTotalList.get(i).roomInUserList.size() + "-");
						}
					} else {
						roomListMessage = "-";
					}
					for (int i = 0; i < waitUserList.size(); i++) {
						waitUserList.get(i).pw.println(Protocol.ROOMMAKE_OK + "|" + roomListMessage); // 룸리스트 새로고침
						waitUserList.get(i).pw.flush();
					}
					System.out.println("방퇴실 동작 부분 --> [대기실 인원 수 ]" + waitUserList.size());
					String userline = ""; // 채팅창에
					for (int i = 0; i < waitUserList.size(); i++) {
						userline = userline + (waitUserList.get(i).user.getIdName() + ":");
					}
					for (int i = 0; i < waitUserList.size(); i++) {
						waitUserList.get(i).pw.println(
								Protocol.EXITWAITROOM + "|" + user.getIdName() + "|님이 대기실에 입장하였습니다.|" + userline); // 대기방에
																													// 메세지
																													// 전송
						waitUserList.get(i).pw.flush();
					}
				} else if (line[0].compareTo(Protocol.CHATTINGSENDMESSAGE) == 0) { // 채팅방에서 메세지 보내기
					int roomUserSize = roomTotalList.get(roomTotalList.indexOf(userRoom)).roomInUserList.size();

					for (int i = 0; i < roomUserSize; i++) {
						roomTotalList.get(roomTotalList.indexOf(userRoom)).roomInUserList.get(i).pw
								.println(Protocol.CHATTINGSENDMESSAGE_OK + "|" + user.getIdName() + "|" + line[i]); // 채팅방
						roomTotalList.get(roomTotalList.indexOf(userRoom)).roomInUserList.get(i).pw.flush();
					}
				} // while
			}
			br.close();
			pw.close();
			socket.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
