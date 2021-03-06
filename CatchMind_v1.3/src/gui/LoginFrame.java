package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import java.awt.GridLayout;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JTextField;

import Client.MainClient;
import dao.UserDao;
import models.User;
import oracle.net.aso.c;

import javax.swing.JPanel;
import javax.swing.JPasswordField;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class LoginFrame extends JFrame {

	private final static String TAG = "SigninFrame : ";

	public LoginFrame loginFrame = this;
	public JPanel pLogin;
	public JButton btID, btPW, btSign, btLogin;
	public JTextField tfID;
	public JPasswordField tfpw;
	public MainClient mainClient;
	public ArrayList<String> userName = new ArrayList<>();

	// 생성자
	public LoginFrame() {
		initObject();
		initData();
		initDesign();
		initListener();
		setVisible(true);
		
	}


	// 객체생성
	private void initObject() {
		pLogin = new JPanel();

		btID = new JButton("아이디");
		btPW = new JButton("비밀번호");
		btSign = new JButton("회원가입");
		btLogin = new JButton("로그인");

		tfID = new JTextField("test");
		tfpw = new JPasswordField("1111");
	}

	// 데이터 초기화
	private void initData() {

	}

	// 디자인
	private void initDesign() {
		// 1. 기본세팅
		loginFrame.setTitle("Login");
		loginFrame.setBounds(100, 100, 393, 269);
		loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		loginFrame.getContentPane().setLayout(new GridLayout(0, 1, 0, 0));

		// 2. 패널세팅
		getContentPane().add(pLogin);
		pLogin.setLayout(null);

		// 3. 디자인
		tfID.setColumns(10);
		tfID.setBounds(197, 46, 128, 24);
		btID.setBounds(67, 45, 105, 27);
		tfpw.setBounds(197, 99, 128, 26);
		btPW.setBounds(67, 99, 105, 27);
		tfpw.setColumns(10);
		btSign.setBounds(94, 160, 89, 27);
		btLogin.setBounds(197, 160, 89, 27);

		// 4. 패널에 컴포넌트 추가
		pLogin.add(btID);
		pLogin.add(tfID);
		pLogin.add(btPW);
		pLogin.add(tfpw);
		pLogin.add(btSign);
		pLogin.add(btLogin);
	}

	// 리스너 등록
	private void initListener() {

		btSign.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new SigninFame(mainClient);
				loginFrame.setVisible(false);
			}
		});
		
		btLogin.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// userdao에 셀렉트문으로 select from where username 이랑 qw가 같을경우 성공/ 리턴이 0개가되면 오류메시지
				UserDao userDao = UserDao.getInstance();
				int result = userDao.로그인(tfID.getText(), tfpw.getText());

				if (result == 1) {
					new GameRoomFrame(tfID.getText());
					loginFrame.setVisible(false);
					// 로그인 성공시 list 에 담아서 push
					userName.add(tfID.getText());
					System.out.println(TAG + "getText 확인 : " + tfID.getText());
					mainClient.userSend(userName);
					System.out.println(TAG + "userName 확인 : " + userName);
					
					
				} else {
					JOptionPane.showMessageDialog(null, "로그인에 실패했습니다.");
					tfID.setText("");
					tfpw.setText("");
				}

			}
		});

	}

}
