package catchcatch.client;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.Writer;
import java.net.Socket;
import java.util.Scanner;

import catchcatch.gui.GameRoomFrame;
import catchcatch.gui.LoginFrame;

public class MainClient {
	
	private final static String TAG = "MainClient : ";

	Socket socket;
	BufferedReader br;
	PrintWriter pw;
	Scanner sc;
	MainClient mainClient = this;
	GameRoomFrame gameRoomFrame;
	
	public MainClient() {
	
		try {
			gameRoomFrame = new GameRoomFrame(mainClient);
			socket = new Socket("localhost", 8888);
			SocketThread st = new SocketThread();
			st.start();
			
			pw = new PrintWriter(socket.getOutputStream(), true);
			sc = new Scanner((Readable) gameRoomFrame.tfChat);
			
			while (true) {
				String msg = sc.nextLine();
				System.out.println(TAG + "pw : " + msg);
				pw.println();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
//	
//	public void send(String msg) {
//		try {
//			System.out.println(TAG + "send() : " + msg);
//			pw.println(msg);			
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	} 

	class SocketThread extends Thread {
		@Override
		public void run() {
			try {
				br = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));
				String msg = null;
				while ((msg = br.readLine()) != null) {
					System.out.println(TAG + "br : " + msg);
//					msg = br.readLine().split(":"); //
					gameRoomFrame.taChat.setText(gameRoomFrame.taChat.getText() + msg + "\n");
				}
			
			} catch (Exception e) {
				e.printStackTrace();
			} 
		}
	}
	
	public static void main(String[] args) {
		new LoginFrame();
	}

}
