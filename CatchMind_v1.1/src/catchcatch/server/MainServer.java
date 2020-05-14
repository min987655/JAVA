package catchcatch.server;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;

import catchcatch.util.StringParser;

public class MainServer {

	ServerSocket ss;
	Vector<SocketThread> vc;
	StringParser msgParser;

	public MainServer() {

		try {
			// 서버 소켓 생성
			ss = new ServerSocket(8888);
			System.out.println("서버준비완료");
			vc = new Vector<>();

			// 메인쓰레드 : 소켓 accept(), vector에 담음.
			while (true) {
				Socket socket = ss.accept();
				SocketThread st = new SocketThread(socket);
				st.start();
				vc.add(st);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		new MainServer();
	}
}
