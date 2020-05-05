package room;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.PrintWriter;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import action.Protocol;

public class DetailPanel extends JPanel implements ActionListener {

	public static String labelName[] = { "방 번호 :", "      ", "방 제목 :", "      ", "인원 수 :", "      ", "      " };

	public JLabel labelArray[]; // 방번호, 방제목, 인원수
	private JButton enterButton;

	private BufferedReader br;
	private PrintWriter pw;

	public DetailPanel(BufferedReader br, PrintWriter pw) {
		this.br = br;
		this.pw = pw;
	}

	public void init() {
		this.setLayout(new GridLayout(5, 2, 1, 1));

		labelArray = new JLabel[labelName.length];

		for (int i = 0; i < labelName.length; i++) {
			labelArray[i] = new JLabel(labelName[i]);
			this.add(labelArray[i]);
		}

		enterButton = new JButton("입  장");
		this.add(enterButton);
		enterButton.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("입장버튼");
		if (e.getSource() == enterButton) {
			String[] count = labelArray[5].getText().split("/");
			if (count[0].compareTo(count[1]) == 0) {
				JOptionPane.showMessageDialog(this, "인원수 초과로 들어갈 수 없습니다.");
			} else {
				// 서버로 입장 요청 --> 룸 ID
				String line = "";
				line = line + (Protocol.ENTERROOM + "|" + labelArray[1].getText()); // Rro + 방번호
				pw.println(line);
				pw.flush();
			}
		}
	}

}
