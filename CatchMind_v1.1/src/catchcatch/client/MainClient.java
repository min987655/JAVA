package catchcatch.client;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.Writer;
import java.net.Socket;
import java.util.Scanner;

import catchcatch.gui.GameRoomFrame;

public class MainClient {

	Socket socket;
	BufferedReader br;
	PrintWriter pw;
	Scanner sc;
	
	public MainClient() {
	
		try {
			socket = new Socket("localhost", 8888);
			SocketThread st = new SocketThread();
			st.start();
			
			pw = new PrintWriter(socket.getOutputStream(), true);
			sc = new Scanner(System.in);
			while (true) {
				String msg = sc.nextLine();
				pw.println();
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}

	class SocketThread extends Thread {
		@Override
		public void run() {

			try {
				br = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));
				String line = null;
				while ((line = br.readLine()) != null) {
					System.out.println("from server : " + line);
				}
			
			} catch (Exception e) {
				e.printStackTrace();
			} 
		}
	}
	
	
	public static void main(String[] args) {
		new GameRoomFrame();
	}

}
