
package chat;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;

public class MySocketServer {

	ServerSocket serverSocket;
	Vector<NewSocketThread> vt; 
	
	public MySocketServer() throws Exception {
		vt = new Vector<>();
		serverSocket = new ServerSocket(3000);
		
		while(true) {
			Socket socket =serverSocket.accept(); // while�� �� ������ socket�� ���� ���� ������ socket�� �÷��ǿ� �����Ͽ��� ��.
			System.out.println("��û�� ����");
			NewSocketThread nt = new NewSocketThread(socket);
			Thread newWorker = new Thread(nt);
			newWorker.start();
			vt.add(nt); // �÷��ǿ� socket ����.
		}
	
	}
	// ���ο� �����忡�� ���۸� ������ �� �ְ� socket�� ���� !
	// ����Ŭ����
	class NewSocketThread implements Runnable {
		
		Socket socket;
		BufferedReader br;
		BufferedWriter bw; 
		
		public NewSocketThread(Socket socket) {
			this.socket = socket;
		}
		
		@Override
		public void run() {
			try {
				br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
				
				String msg = "";
				while((msg = br.readLine()) != null) {
					for (NewSocketThread newSocketThread : vt) {
						System.out.println("Ŭ���̾�Ʈ : "+msg);
						if(newSocketThread != this) { // this�� �ڱ��ڽ��̴ϱ� �ڱ� �ڽŰ� ���� ���� ���� �ѷ���
							newSocketThread.bw.write(msg+"\n");
							newSocketThread.bw.flush();
						}

					}
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) {
		try {
			new MySocketServer();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
