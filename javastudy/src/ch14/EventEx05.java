package ch14;

import java.awt.Container;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class EventEx05 extends JFrame {

	private JLabel la;

	public EventEx05() {
		la = new JLabel("Hello");
		setTitle("MouseEvent ����");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container c = getContentPane();
		c.addMouseListener(new MyMouseListener());
		c.setLayout(null); // absoulte ���̾ƿ�
		la.setSize(50, 20); // ���� ������
		la.setLocation(30, 30); // ���� ��ġ
		c.add(la);
		setSize(250, 250);
		setVisible(true);
	}

	class MyMouseListener extends MouseAdapter {

		// ���콺�� Ŭ���ϰ� ���� ��
		@Override
		public void mousePressed(MouseEvent e) {

			new Thread(new Runnable() {
			
				@Override
				public void run() {
					int x = e.getX(); // X ��ǥ
					int y = e.getY(); // Y ��ǥ
					
					for (int i = 0; i < y; i++) {
						y = y - y;
						la.setLocation(30, y);
						try {
							Thread.sleep(1000);
						} catch (InterruptedException e1) {
							e1.printStackTrace();
						}
					}

				}
			}).start();

		}
	}

	public static void main(String[] args) {
		new EventEx05();
	}

}