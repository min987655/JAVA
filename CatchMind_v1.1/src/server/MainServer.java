package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;

import util.MSGParser;

public class MainServer {

	ServerSocket serverSocket;
	Vector<SocketThread> vc;
	MSGParser msgParser;
	
	public MainServer() {
		
		try {
			serverSocket = new ServerSocket(8888);
			vc = new Vector<>();
			
			while (true) {
				Socket socket = serverSocket.accept();
				SocketThread st = new SocketThread(socket);
				st.start();
				vc.add(st);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	class SocketThread extends Thread {
	
		Socket socket;
		String name;
		BufferedReader br;
		PrintWriter pw;
		
		public SocketThread(Socket socket) {
			this.socket = socket;
		}
	
		@Override
		public void run() {
			
			try {
				br = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));
				pw = new PrintWriter(socket.getOutputStream(), true);

				String line = null;
				while ((line = br.readLine()) != null) {
					msgParser(line);
				}
			} catch (Exception e) {
				e.printStackTrace();			
		}
	}
	
	public static void main(String[] args) {
		new MainServer();
	}
}
