package action;

import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;

import info.Rooms;

public class MainServer {

	private ServerSocket ss; // 서버 소켓
	private ArrayList<MainHandler> allUserList; // 전체 사용자
	private ArrayList<MainHandler> waitUserList; // 대기실 사용자
	private ArrayList<Rooms> roomTotalList; // 전체 방 리스트

	private Connection conn;
	private String driver = "oracle.jdbc.driver.OracleDriver";
	private String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private String user = "chat";
	private String password = "bitc5600";

	public MainServer() {
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, user, password); // DB 연결

			ss = new ServerSocket(9500);
			System.out.println("서버준비 완료");

			allUserList = new ArrayList<MainHandler>(); // 전체 사용자
			waitUserList = new ArrayList<MainHandler>(); // 대기실 사용자
			roomTotalList = new ArrayList<Rooms>();
			while (true) {
				Socket socket = ss.accept();
				MainHandler handler = new MainHandler(socket, allUserList, waitUserList, roomTotalList, conn); // 스레드 생성
				handler.start(); // 스레드 시작
				allUserList.add(handler);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		new MainServer();
	}
}
