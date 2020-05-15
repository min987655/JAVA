package catchcatch.gui;

import java.awt.EventQueue;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Vector;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import catchcatch.models.Users;
import catchcatch.server.MainServer;

public class GameRoomFrame {

	private Vector<MainServer> vc;
	private JFrame frame;
	private JTextField tfCard;
	private JTextField tfChat;
	private JList<Users> userList;
	private DefaultListModel<Users> listModel;
	private JButton btGstart, btEnter;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GameRoomFrame window = new GameRoomFrame();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public GameRoomFrame() {
		initialize();
	}
	
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 962, 738);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btCard = new JButton("제시어");
		btCard.setBounds(40, 46, 112, 34);
		frame.getContentPane().add(btCard);
		
		// 제시어 뿌려줌
		tfCard = new JTextField(); 
		tfCard.setBounds(160, 46, 262, 34);
		frame.getContentPane().add(tfCard);
		tfCard.setColumns(10);
		
		JPanel panel = new JPanel();
		panel.setBounds(40, 106, 502, 541);
		frame.getContentPane().add(panel);
		
		JButton btGstart = new JButton("게임시작");
		btGstart.setBounds(580, 46, 323, 63);
		frame.getContentPane().add(btGstart);
		
		JLabel LuserList = new JLabel("User List");
		LuserList.setBounds(579, 121, 308, 27);
		frame.getContentPane().add(LuserList);

//		// 참여유저
//		JTextArea taUserList = new JTextArea(); 
//		taUserList.setBounds(580, 157, 323, 120);
//		frame.getContentPane().add(taUserList);
		
		// 유저리스트
		listModel = new DefaultListModel<>();
		userList = new JList<>(listModel);
		userList.setBounds(580, 157, 323, 120);
		frame.getContentPane().add(userList);		
		
		// 채팅리스트
		JTextArea taChat = new JTextArea();
		taChat.setBounds(580, 292, 323, 305);
		frame.getContentPane().add(taChat);

		// 채팅
		tfChat = new JTextField();
		tfChat.setBounds(580, 609, 229, 38);
		frame.getContentPane().add(tfChat);
		tfChat.setColumns(10);
		
		JButton btEnter = new JButton("Enter");
		btEnter.setBounds(823, 609, 80, 38);
		frame.getContentPane().add(btEnter);
	}
	// 데이터 초기화
	private void initData() {
		for (int i = 1; i<=vc.size(); i++) {  
//			listModel.addElement(new Users(i, "홍길동"));
		}
	}
	// 리스너 등록
	private void initListener() {
		btGstart.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
			}
		});
	}
}