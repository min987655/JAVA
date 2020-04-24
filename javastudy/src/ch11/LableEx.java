package ch11;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class LableEx extends JFrame {

	public LableEx() {
		setTitle("���̺� ����");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container c = getContentPane();
		BorderLayout border = new BorderLayout();
		c.setLayout(border);
		
		// ���ڿ� ���̺� ����
		JLabel textLabel = new JLabel("����մϴ�");
		
		//�̹��� ���̺� ����
		ImageIcon beauty = new ImageIcon("img/beauty.jpg"); // �̹��� �ε�
		JLabel imageLabel = new JLabel(beauty); // �̹��� ���̺� ����
		
		// ���ڿ��� �̹����� ��� ���� ���̺� ����
		ImageIcon normalIcon = new ImageIcon("img/normalIcon.gif"); // �̹��� �ε�
		JLabel label = new JLabel("���������� ��ȭ�ϼ���.", normalIcon, SwingConstants.CENTER); // ���̺� ����
		
		// ����Ʈ�ҿ� 3���� ���̺� ����
		c.add(textLabel,border.NORTH);
		c.add(imageLabel,border.CENTER);
		c.add(label,border.SOUTH);
		
		setSize(400, 600);
		setVisible(true);
	}

	public static void main(String[] args) {
		new LableEx();
	}

}