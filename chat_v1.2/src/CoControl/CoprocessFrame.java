package CoControl;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.io.BufferedReader;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

public class CoprocessFrame extends JFrame {

	public JButton openB, exitB, sendB;
	public JTextArea area, area1, partList;
	public JTextField field, quiz;

	public JList<String> list2;
	private BufferedReader br;
	private ArrayList<String> arr;
	public DefaultListModel<String> model;

	public CoprocessFrame() {

		quiz = new JTextField(18);
		exitB = new JButton("나가기");

		quiz.setEditable(true);
		quiz.setBackground(Color.WHITE);
		exitB.setEnabled(true);

		JPanel wpanel = new JPanel(new GridLayout(1, 5, 5, 0));
		wpanel.add(quiz);
		wpanel.add(exitB);

		JPanel wpanel1 = new JPanel();
		area = new JTextArea();
		JScrollPane scroll = new JScrollPane(area);
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scroll.setPreferredSize(new Dimension(700, 735));
		wpanel1.add(scroll);

		JPanel totwpanel = new JPanel(new BorderLayout());
		totwpanel.add("North", wpanel);
		totwpanel.add("Center", wpanel1);

		JPanel epanel = new JPanel(new BorderLayout());
		JPanel p1 = new JPanel();
		JLabel user = new JLabel("                                   참여 인원");
		p1.add(user);

		partList = new JTextArea();
		partList.setEditable(true);
		JScrollPane scroll1 = new JScrollPane(partList);
		scroll1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scroll1.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		epanel.add("North", user);
		epanel.add("Center", scroll1);

		JPanel epanel1 = new JPanel(new BorderLayout());
		JPanel p2 = new JPanel();
		JLabel file = new JLabel("                               점수판");
		p2.add(file);

		list2 = new JList<String>(new DefaultListModel<String>());
		list2.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		model = (DefaultListModel<String>) list2.getModel();
		JScrollPane scroll2 = new JScrollPane(list2);
		scroll2.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scroll2.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		epanel1.add("North", file);
		epanel1.add("Center", scroll2);

		JPanel epanel12 = new JPanel(new GridLayout(2, 1, 0, 10));
		epanel12.add(epanel);
		epanel12.add(epanel1);

		JPanel epanel3 = new JPanel(new BorderLayout());

		JPanel p3 = new JPanel();
		JLabel chat = new JLabel("                                        채 팅");
		p3.add(chat);

		area1 = new JTextArea();
		JScrollPane scroll3 = new JScrollPane(area1);
		scroll3.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scroll3.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
		area1.setEditable(false);
		epanel3.add("North", chat);
		epanel3.add("Center", scroll3);

		JPanel epanel4 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		field = new JTextField(18);
		sendB = new JButton("Enter");
		epanel4.add(field);
		epanel4.add(sendB);

		JPanel totepanel = new JPanel(new BorderLayout());
		totepanel.add("North", epanel12);
		totepanel.add("Center", epanel3);
		totepanel.add("South", epanel4);
		// *************************************************
		Container c = this.getContentPane();
		c.add("Center", totwpanel);
		c.add("East", totepanel);

		setResizable(false);
		setBounds(400, 200, 1000, 800);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

	}
}
