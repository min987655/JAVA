package catchcatch.gui;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.PrintWriter;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import catchcatch.client.MainClient;

public class GameRoomFrame extends JFrame {

	private final static String TAG = "GameroomFrame : ";

	private GameRoomFrame gameroomFrame = this;
	private MainClient mainClient;
	
	public JTextField tfCard, tfChat;
	public JButton btEnter, btCard, btGstart;
	public JTextArea taChat, taUserList;
	public JLabel LuserList;
	public JPanel Canvas;
//	private Drawing dw;
//	private MyCanvas2 cv2;

	public GameRoomFrame(MainClient mainClient) {
		this.mainClient = mainClient;
		initObject();
//		initData();
		initDesign();
		initListener();
	}

	// 객체생성
	public void initObject() {
		btCard = new JButton("제시어");
		btGstart = new JButton("게임시작");
		tfCard = new JTextField();
		Canvas = new JPanel();
		LuserList = new JLabel("User List");
		taUserList = new JTextArea();
		tfChat = new JTextField();
		btEnter = new JButton("Enter");
		taChat = new JTextArea();
	}
//
//	// 데이터초기화
//	private void initData() {
//
//	}

	// 디자인
	public void initDesign() {
		// 1. 기본세팅
		setTitle("Game Room");
		setBounds(100, 100, 962, 738);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(null);

		// 2. 패널세팅
		btCard.setBounds(40, 46, 112, 34);
		tfCard.setBounds(160, 46, 262, 34);
		tfCard.setColumns(10);
		btGstart.setBounds(580, 46, 323, 63);
		LuserList.setBounds(579, 121, 308, 27);
		taUserList.setBounds(580, 157, 323, 120);
		Canvas.setBounds(40, 106, 502, 541);
		taChat.setBounds(580, 292, 323, 305);
		tfChat.setBounds(580, 609, 229, 38);
		tfChat.setColumns(10);
		btEnter.setBounds(823, 609, 80, 38);

		// 3. 패널에 컴포넌트 추가
		getContentPane().add(btCard);
		getContentPane().add(tfCard);
		getContentPane().add(Canvas);
		getContentPane().add(btGstart);
		getContentPane().add(LuserList);
		getContentPane().add(taUserList);
		getContentPane().add(taChat);
		getContentPane().add(tfChat);
		getContentPane().add(btEnter);
	}

	// 리스너 등록
	private void initListener() {

		btEnter.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.out.println(TAG + "btEnter 클릭 이벤트 : " + tfChat.getText());
				taChat.append(tfChat.getText());
				mainClient.send(tfChat.getText());
				tfChat.setText("");
			}
		});
	}
}