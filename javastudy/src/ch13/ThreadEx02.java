package ch13;

class DownloadThread implements Runnable {
	// �񵿱� ���α׷� 
	// ���� ������� ������� ����
	// ������ ��� �������� �ʰ� os���� �����带 �ϳ� �� ����� �����϶�� ��.
	// �ݾ� 30000���� �ȳ���(Ÿ�̹��� ����)
	int data = 10000;
	
	@Override
	public void run() {
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		data = data + 20000;
		System.out.println("�ݾ� �ٿ�ε� ����");
	}
}

public class ThreadEx02 {
	public static void main(String[] args) {
		System.out.println("���α׷� ����");
		System.out.println("-------------");
		
		DownloadThread dt = new DownloadThread();
		Thread t1 = new Thread(dt);
		t1.start();
		
		System.out.println("�ݾ��� : "+dt.data);
	}
}
