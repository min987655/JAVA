package ch14;

import java.awt.Container;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class EventEx04 extends JFrame {

	private JLabel la;

	public EventEx04() {
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
			int x = e.getX(); // X ��ǥ
			int y = e.getY(); // Y ��ǥ
			la.setLocation(x, y); // �� ��ġ ���� = repaint()
		}
	}

	public static void main(String[] args) {
		new EventEx04();
	}

}