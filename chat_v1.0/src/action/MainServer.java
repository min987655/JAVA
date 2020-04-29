package action;

import java.net.ServerSocket;
import java.sql.Connection;
import java.util.ArrayList;

import DO.Room;

public class MainServer {
	
	private ServerSocket ss; // 서버 소켓
	private ArrayList<MainHandler> allUserList; // 전체 사용자
	private ArrayList<MainServer> WaitUserList; // 대기실 사용자
	private ArrayList<Room> roomtotalList; // 전체 방 리스트
	
	private Connection conn;
	

	public static void main(String[] args) {
		new MainServer();
	}
}
