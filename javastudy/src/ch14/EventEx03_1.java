package ch14;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class EventEx03_1 extends JFrame {

	public EventEx03_1() {
		setTitle("Action �̺�Ʈ ������ ����");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container c = getContentPane();
		c.setLayout(new FlowLayout());
		JButton btn = new JButton("Action");
		c.add(btn);

		// �͸�Ŭ������ Action ������ ����
		btn.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				JButton b = (JButton)e.getSource();
				if (b.getText().equals("Action")) {
					b.setText("�׼�");
				} else {
					b.setText("Ation");
				}
				
				// EventEx04�� ����� JFrame�� ����� ȣ���� �� ����.
				setTitle(b.getText());
			}
		});
				
		setSize(350, 150);
		setVisible(true);
	}

	public static void main(String[] args) {
		new EventEx03_1();
	}
}