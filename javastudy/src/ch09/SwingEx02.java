package ch09;

import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;

public class SwingEx02 extends JFrame {

	public SwingEx02() {
		setTitle("ContentPane�� JFrame"); // ������ Ÿ��Ʋ �ޱ�
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // ������ �����츦 ������ ���� ���α׷��� ����

		Container contentPane = getContentPane(); // ����Ʈ ���� �˾Ƴ���.
		contentPane.setBackground(Color.BLACK); // ����Ʈ�� ��׶��� �÷� ����.
		contentPane.setLayout(new FlowLayout()); // ����Ʈ�� �⺻ �������� FlowLayout����

		contentPane.add(new JButton("OK"));
		contentPane.add(new JButton("Cancel"));
		contentPane.add(new JButton("Ignore"));

		setSize(300, 150);
		setVisible(true); // ȭ�鿡 ������ ���
	}

	public static void main(String[] args) {
		new SwingEx02();
	}

}