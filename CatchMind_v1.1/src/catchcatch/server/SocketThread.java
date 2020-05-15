package catchcatch.server;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Vector;

import catchcatch.util.Protocol;

// 소켓정보 + 타겟(run) + 식별자
class SocketThread extends Thread {

	Socket socket;
	String name;
	BufferedReader br;
	PrintWriter pw;
	Vector<SocketThread> vc;
	
	public SocketThread(Socket socket) {
		this.socket = socket;
	}

	@Override
	public void run() {
		
		try {
			br = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));
			pw = new PrintWriter(socket.getOutputStream(), true);

			String msg = "";
			while ((msg = br.readLine()) != null) {
				router(msg);
			}
		} catch (Exception e) {
			e.printStackTrace();			
	}
}
	
	public void router(String line) {
		
		String[] gubun = line.split(":");
		String protocol = gubun[0];
		if (protocol.equals(Protocol.CHAT)) {
			String msg = gubun[1];
			Chat(msg);
		}
	}
	
	public void Chat(String msg) {
		System.out.println(msg + "ip : " + socket.getInetAddress());
		for (SocketThread socketThread : vc) {
			socketThread.pw.println(msg);
		}
	}
	
}
