package action;

import java.net.ServerSocket;
import java.sql.Connection;
import java.util.ArrayList;

import DO.Room;

public class MainServer {
	
	private ServerSocket ss; // ���� ����
	private ArrayList<MainHandler> allUserList; // ��ü �����
	private ArrayList<MainServer> WaitUserList; // ���� �����
	private ArrayList<Room> roomtotalList; // ��ü �� ����Ʈ
	
	private Connection conn;
	

	public static void main(String[] args) {
		new MainServer();
	}
}
