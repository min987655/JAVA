package room;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.io.BufferedReader;
import java.io.PrintWriter;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;

public class RoomFrame extends JFrame {
	public JButton makeB, exitB, sendB, enterB;
	public JPanel upP, chatP, chatP1, listP, listP1, listP2, roomP, roomP1, sumP, centerP;
	private JPanel[] sortrm;
	public JTextArea chatA, userA;
	public JTextField chatT, tx1, tx2, tx3, tx4, tx5, tx6, tx7, tx8;
	private JLabel la1, la2, la3, la4, la5, la6;
	private JList<String> entList, frList;
	private EtchedBorder eb;
	private JList<DetailPanel> list;
	private DefaultListModel<DetailPanel> model;
	public DefaultListModel<String> userWaitModel;
	public DetailPanel[] dp;
	RoomMake rmake;

	private BufferedReader br;
	private PrintWriter pw;

	public RoomFrame(BufferedReader br, PrintWriter pw) {
		this.br = br;
		this.pw = pw;

		// 상단버튼
		dp = new DetailPanel[100];
		upP = new JPanel(new FlowLayout());
		la6 = new JLabel("정  렬 : ");
		makeB = new JButton("방 만 들 기");
		makeB.setPreferredSize(new Dimension(400, 30));
		exitB = new JButton("exit");
		exitB.setPreferredSize(new Dimension(200, 30));
		upP.add(la6);
		upP.add(makeB);
		upP.add(exitB);

		// 채팅방 목록
		roomP = new JPanel(new BorderLayout());
		la4 = new JLabel("채팅방 목록");
		la4.setFont(new Font("나눔스퀘어", Font.PLAIN, 20));

		centerP = new JPanel(new GridLayout(100, 2, 10, 10)); // 100개
		for (int i = 0; i < 100; i++) {
			dp[i] = new DetailPanel(br, pw);
			centerP.add(dp[i]);
		}
		JScrollPane scrollRoomList = new JScrollPane(centerP);
		scrollRoomList.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scrollRoomList.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollRoomList.getVerticalScrollBar().setValue(scrollRoomList.getVerticalScrollBar().getMaximum());

		roomP.add("Center", scrollRoomList);
		roomP.add("North", la4);

		// 대기자 채팅목록
		chatP = new JPanel(new BorderLayout());
		la1 = new JLabel("대기자 채팅방");
		la1.setFont(new Font("나눔스퀘어", Font.PLAIN, 15));
		chatA = new JTextArea();
		chatA.setEditable(false);
		JScrollPane scroll = new JScrollPane(chatA);
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scroll.setPreferredSize(new Dimension(400, 250));
		scroll.getVerticalScrollBar().setValue(scrollRoomList.getVerticalScrollBar().getMaximum());

		chatP1 = new JPanel(new FlowLayout(FlowLayout.LEFT));

		chatT = new JTextField(30);
		sendB = new JButton("보내기");

		chatP1.add(chatT);
		chatP1.add(sendB);

		chatP.add("Center", scroll);
		chatP.add("South", chatP1);
		chatP.add("North", la1);

		// 채팅 대기자 목록
		listP = new JPanel(new GridLayout(2, 1, 20, 20));

		listP1 = new JPanel(new BorderLayout());
		la2 = new JLabel(" 대기실 인원 ");
		la2.setFont(new Font("나눔스퀘어", Font.PLAIN, 15));

		userA = new JTextArea();
		userA.setEditable(false);

		JScrollPane scroll1 = new JScrollPane(userA);
		scroll1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scroll1.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

		listP1.add("North", la2);
		listP1.add("Center", scroll1);

		listP2 = new JPanel(new BorderLayout());
		listP2.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));

		la3 = new JLabel(" 친 구 목 록 ");
		la3.setFont(new Font("나눔스퀘어", Font.PLAIN, 15));
		frList = new JList<String>(new DefaultListModel<String>());
		JScrollPane scroll2 = new JScrollPane(frList);
		scroll2.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scroll2.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

		listP2.add("North", la3);
		listP2.add("Center", scroll2);

		listP.add(listP1);
		listP.add(listP2);

		// 대기자 목록 + 대기자 채팅
		sumP = new JPanel(new BorderLayout());

		sumP.add("Center", listP);
		sumP.add("South", chatP);

		// 종합
		Container contentPane = this.getContentPane();
		contentPane.add("East", sumP);
		contentPane.add("North", upP);
		contentPane.add("Center", roomP);

		setBounds(400, 200, 1000, 800);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

	} // 생성자

	public void containPanelClear() {
		centerP.removeAll();
		for (int i = 0; i < 100; i++) {
			dp[i] = new DetailPanel(br, pw);
			centerP.add(dp[i]);
		}

	}

}
