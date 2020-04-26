package ch10;

import java.awt.Component;
import java.awt.Container;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.*;

public class FlyingTextEx extends JFrame {
	private final int FLYING_UNIT = 10; // ���̺��� �ѹ� �����̴� ������ 10�ȼ�
	private JLabel la = new JLabel("HELLO"); // Ű �Է¿� ���� ������ ���̺� ������Ʈ
	
	public FlyingTextEx() {
		setTitle("��,��,��,�� Ű�� �̿��Ͽ� �ؽ�Ʈ �����̱�");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Container c = getContentPane();
		c.setLayout(null); // ����Ʈ���� ��ġ������ ����
		c.addKeyListener(new MyKeyListener());
		la.setLocation(50, 50); // la �ʱ���ġ
		la.setSize(100, 20);
		c.add(la);
		
		setSize(300, 300);
		setVisible(true);
		c.setFocusable(true);
		c.requestFocus(); // ����Ʈ���� Ű �Է� ���� �� �ֵ��� ��Ŀ�� ���� ����
		
		// ���� �ڵ�� ����Ʈ�ҿ� ��Ŀ���� ���� ��� ���콺�� Ŭ���ϸ� �ٽ� ��Ŀ�� ��� ��
		c.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Component com = (Component)e.getSource(); // ���콺�� Ŭ���� ������Ʈ
				com.setFocusable(true);
				com.requestFocus(); // ���콺�� Ŭ���� ������Ʈ���� ��Ŀ�� ����
			}
		});
	}
	
	class MyKeyListener extends KeyAdapter {
		@Override
		public void keyPressed(KeyEvent e) {
			int keyCode = e.getKeyCode();
			
			// Ű �ڵ� ��(keyCode)�� ���� ��,��,��,�� Ű�� �Ǻ��ϰ� la�� ��ġ�� �̵���Ų��.
			switch (keyCode) {
			case KeyEvent.VK_UP:
				la.setLocation(la.getX(), la.getY()-FLYING_UNIT);
				break;
			case KeyEvent.VK_DOWN:
				la.setLocation(la.getX(), la.getY()+FLYING_UNIT);
				break;
			case KeyEvent.VK_LEFT:
				la.setLocation(la.getX()-FLYING_UNIT, la.getY());
				break;
			case KeyEvent.VK_RIGHT:
				la.setLocation(la.getX()+FLYING_UNIT, la.getY());
				break;
			}
		}
	}
	
	public static void main(String[] args) {
		new FlyingTextEx();
	}

}
