package ch11;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.ScrollPane;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class TextFieldEx  extends JFrame {

	private JTextField tf;
	private JTextArea ta;
	private ScrollPane sp; // JScrollPane�� ������ ��ƸԾ����. ��� X
	
	public TextFieldEx() {
		init(); // new�� ������ �ȸ¾� ������ �� ����.
		
		setTitle("�ؽ�Ʈ ������, �ؽ�Ʈ �ڽ� ����");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Container c = getContentPane();
		
		// �ؽ�Ʈ�ʵ� ������
		tf.setFont(new Font("Arial", Font.BOLD, 30));
		tf.setPreferredSize(new Dimension(100,100)); // x,y ��� ������Ʈ ũ�� ����
		

		ta.setBackground(Color.ORANGE);
		
		ta.setEditable(false); // ���� ������ ��.
		ta.setFont(new Font("Arial", Font.BOLD, 60));
		ta.setForeground(Color.GRAY);
		
		sp.add(ta); // ta�� ��ũ�ѿ� ����. ���� �߿�
		
		
		tf.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					String value = tf.getText();
					ta.append(value+"\n");
					tf.setText("");
				}
				
			}
		});
		c.add(tf, BorderLayout.SOUTH);
		c.add(sp, BorderLayout.CENTER);
		
		setSize(400, 500);
		setVisible(true);
	}
	
	private void init() { // GUI���α׷����� init�� ����� new���� ���� ��Ƴ���. new ������ ������ ���� ��.
		tf = new JTextField("", 20);
		sp = new ScrollPane();
		ta = new JTextArea("", 5, 20);
	}
	
	public static void main(String[] args) {
		new TextFieldEx();
	}

}