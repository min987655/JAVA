package Client;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

public class LoginFrame extends JFrame {

	private JPanel mp;
	private JLabel idlabel;
	private JButton loginbutton;
	private JTextField idfield;

	LoginFrame(Object c) {
		setTitle("Login");
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		mp = new JPanel();
		idlabel = new JLabel("닉네임");

		idfield = new JTextField(10);

		loginbutton = new JButton("입장하기");
		loginbutton.addActionListener((ActionListener) c);

		add(mp);

		mp.setLayout(new FlowLayout());

		mp.add(idlabel);
		mp.add(idfield);
		mp.add(loginbutton);
		setBounds(650, 350, 350, 80);
	}

	public String getID() {
		return idfield.getText();
	}

	public String getloginbuttonactioncommand() {
		return loginbutton.getActionCommand();
	}

}