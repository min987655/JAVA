package ch09;

import java.awt.Container;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class GridLayoutEx01 extends JFrame {

	public GridLayoutEx01() {
		setTitle("GridLayout Sample");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// 4*2 ������ GridLayout ��ġ������ ����
		GridLayout grid = new GridLayout(4, 2);
		grid.setVgap(5); // ���� ������ ���� ������ 5 �ȼ��� ����

		Container c = getContentPane();
		c.setLayout(grid); // grid�� ����Ʈ���� ��ġ�����ڷ� ����
		c.add(new JLabel("�̸�"));
		c.add(new JTextField(""));
		c.add(new JLabel("�й�"));
		c.add(new JTextField(""));
		c.add(new JLabel("�а�"));
		c.add(new JTextField(""));
		c.add(new JLabel("����"));
		c.add(new JTextField(""));

		setSize(300, 200);
		setVisible(true);
	}

	public static void main(String[] args) {
		new GridLayoutEx01();
	}

}