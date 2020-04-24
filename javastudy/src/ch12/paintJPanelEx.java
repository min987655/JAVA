package ch12;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class paintJPanelEx extends JFrame {
	private MyPanel pane1 = new MyPanel();

	public paintJPanelEx() {
		setTitle("Jpanel�� paintComponent() ����");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setContentPane(pane1); // ������ pane1 �г��� ����Ʈ������ ���
		setSize(250, 200);
		setVisible(true);
	}

	// JPanel�� ��ӹ޴� �� �г� ����
	public class MyPanel extends JPanel {
		public void paintComponent(Graphics g) {
			super.paintComponent(g); // JPanel�� paintComponent() ȣ��
			g.setColor(Color.blue); // �Ķ��� ����
			g.drawRect(10, 10, 50, 50); // (10,10) ��ġ�� 50X50 ũ���� ����� �׸���
			g.drawRect(50, 50, 50, 50); // (50,50) ��ġ�� 50X50 ũ���� ����� �׸���
			g.setColor(Color.MAGENTA); // ����Ÿ�� ����
			g.drawRect(90, 90, 50, 50); // (90,90) ��ġ�� 50X50 ũ���� ����� �׸���
		}

	}

	public static void main(String[] args) {
		new paintJPanelEx();
	}

}