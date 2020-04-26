package ch10;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class KeyCodeEx extends JFrame {
	private JLabel la = new JLabel(); // �� ���̺� ������Ʈ ����

	public KeyCodeEx() {
		setTitle("Key Code ���� : F1Ű �ʷϻ�, %Ű �����");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		Container c = getContentPane();
		c.addKeyListener(new MyKeyListener());
		c.add(la);
		setSize(300, 150);
		setVisible(true);
	
		// ����Ʈ���� Ű �Է� ���� �� �ֵ��� ��Ŀ�� ���� ����
		c.setFocusable(true);
		c.requestFocus();
	}

	// key ������ ����
	class MyKeyListener extends KeyAdapter {
		@Override
		public void keyPressed(KeyEvent e) {
			Container contantPane = (Container)e.getSource();
			
			// la�� �Էµ� Ű�� Ű �̸� ���ڿ��� ����Ͽ� ����ڿ��� ������
			la.setText(KeyEvent.getKeyText(e.getKeyCode())+"Ű�� �ԷµǾ���");
			
			if (e.getKeyChar() == '%') { 
				contantPane.setBackground(Color.YELLOW);
			} else if(e.getKeyCode() == KeyEvent.VK_F1) { // �Էµ� Ű�� F1�ΰ�
				contantPane.setBackground(Color.GREEN);
			}
		}
	}

	public static void main(String[] args) {
		new KeyCodeEx();
	}

}
