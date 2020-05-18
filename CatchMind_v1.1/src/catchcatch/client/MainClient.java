package catchcatch.client;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Scanner;

import catchcatch.gui.GameRoomFrame;
import catchcatch.gui.LoginFrame;

public class MainClient {
	
	private final static String TAG = "MainClient : ";

	Socket socket;
	BufferedReader br;
	BufferedWriter bw;
//	Scanner sc;
	MainClient mainClient = this;
	GameRoomFrame gameRoomFrame;
//	String msg = null;
	
	public MainClient() {
	
		try {
			gameRoomFrame = new GameRoomFrame(mainClient);
			socket = new Socket("localhost", 8888);
			SocketThread st = new SocketThread();
			st.start();
			
			bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), "UTF-8"));
			br = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));
		} catch (Exception e) {
			System.out.println(TAG + e.getMessage());
			e.printStackTrace();
		} 
	}
	
	public void send(String msg) {
		try {
			while (true) {
				System.out.println(TAG + "send() : " + msg);
				bw.write(msg);
				bw.flush();				
			}
		} catch (Exception e) {
			System.out.println(TAG + "send() : " + e.getMessage());
			e.printStackTrace();
		}
	} 

	class SocketThread extends Thread {
		@Override
		public void run() {
			try {
				br = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));
				String msg = null;
				while ((msg = br.readLine()) != null) {
//					msg = br.readLine().split(":"); //
					gameRoomFrame.taChat.setText(gameRoomFrame.taChat.getText() + msg + "\n");
					
				}
			
			} catch (Exception e) {
				System.out.println(TAG + "br : " + e.getMessage());
				e.printStackTrace();
			} 
		}
	}
	
//	public void Read(String msg) {
//		try {
//			while((msg = br.readLine()) != null) {
//				// ta뿌리기
//				gameRoomFrame.taChat.setText(gameRoomFrame.taChat.getText() + msg + "\n");
//			}
//		} catch (Exception e) {
//			System.out.println(TAG + "Read() : " + e.getMessage());			
//			e.printStackTrace();
//		}
//	}
	
	public static void main(String[] args) {
		new LoginFrame();
	}

}
