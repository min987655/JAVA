package login;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import action.Protocol;
import cocontrol.CoprocessFrame;
import room.RoomFrame;
import room.RoomMake;

public class EnterFrame extends JFrame implements ActionListener, Runnable {
	private JPasswordField pwT;
	private JTextField idT;
	private JButton idB, pwB, accessB, membershipB;
	private JLabel loginL, logoutL;
	private ImageIcon loginI, logoutI, modifiedI;
	private Socket socket;
	private BufferedReader br;
	private PrintWriter pw;

	MembershipB membershipF; // 회원가입
	RoomFrame RoomF; // 대기실
	RoomMake rMakeF; // 방 만들기
	CoprocessFrame chattingF;

	private String sNumber = "><^^"; // default 시크릿 넘버

	private boolean condition_Id = false; // ID 중복체크
	
	public EnterFrame() {
		network();
		
		membershipF = new MembershipB();
		RoomF = new RoomFrame(br, pw);
		rMakeF = new RoomMake();
		chattingF = new CoprocessFrame();
		
		idB = new JButton("아이디");
		idT = new JTextField(15);
		pwB = new JButton("패스워드");
		pwT = new JPasswordField(15);
		pwT.setEchoChar('*');
		
		JPanel p2 = new JPanel(new FlowLayout());
		p2.add(idB);
		p2.add(idT);
		p2.add(pwB);
		p2.add(pwT);
		
		membershipB = new JButton("회원가입");
		accessB = new JButton("입장");
		
		JPanel p3 = new JPanel();
		p3.add(membershipB);
		p3.add(accessB);
		
		loginI = new ImageIcon("img/loginButton.png");
		loginL = new JLabel(logoutI);
		
		JPanel p4 = new JPanel();
		p4.add(loginL);
		
		Container c = this.getContentPane();
		c.add("Center", p2);
		c.add("South", p3);
		c.add("East", p4);
		
		setVisible(true);
		setResizable(false);
		setBounds(400, 200, 1000, 800);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		event();
	}

	public void event() {
		// 회원가입 관련
		membershipB.addActionListener(this); // 회원가입(버튼)
		// 로그인 관련
		accessB.addActionListener(this); // 입장(login)
		RoomF.exitB.addActionListener(this); // Room --> 로그인 페이지
		// 메세지 관련
		RoomF.sendB.addActionListener(this); // 대기방에서 전송
		// 방 관련
		RoomF.makeB.addActionListener(this);
		rMakeF.makeB.addActionListener(this);
		rMakeF.canB.addActionListener(this);
		chattingF.exitB.addActionListener(this);
		chattingF.sendB.addActionListener(this);
	}

	public void network() {
		// 소켓 생성
		try {
			socket = new Socket("localhost", 9500);
			br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
		} catch (Exception e) {
			System.out.println("서버 오류");
			e.printStackTrace();
			System.exit(0);
		}
		// 스레드 생성
		Thread t = new Thread(this);
		t.start();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == membershipB) { // 메인페이지 --> 회원가입 버튼
			this.setVisible(false);
			membershipF.setVisible(true);
		} else if (e.getSource() == membershipF.joinB) { // 회원가입 페이지 --> 가입버튼
			String id = membershipF.idT.getText();
			String pw1 = membershipF.pwT.getText();

			if (id.length() == 0 || pw1.length() == 0) {
				JOptionPane.showMessageDialog(this, "빈칸을 입력해주세요.");
			} else if (condition_Id) { // ID 중복확인 인증 됨
				String line = "";
				line = line + (membershipF.idT.getText() + "%" + membershipF.pwT.getText());
				System.out.println(line);

				pw.println(Protocol.REGISTER + "|" + line);
				pw.flush();
				JOptionPane.showMessageDialog(this, "회원가입 완료");
				membershipF.setVisible(false);
				this.setVisible(true);

				membershipF.idT.setText("");
				membershipF.pwT.setText("");

				condition_Id = false;
				sNumber = "><^^";

			} else if (!condition_Id) {
				JOptionPane.showMessageDialog(this, "ID 중복확인 해주세요.");
			}
		} else if (e.getSource() == membershipF.calneB) { // 회원가입페이지 --> 취소
			membershipF.setVisible(false);
			this.setVisible(true);
			sNumber = "><^^";
		} else if (e.getSource() == membershipF.idoverlapB) { // 회원가입 페이지ID --> 중복확인
			if (membershipF.idT.getText().length() == 0) {
				JOptionPane.showMessageDialog(this, "아이디를 입력하세요.");
			} else {
				pw.println(Protocol.IDSEARCHCHECK + "|" + membershipF.idT.getText());
				pw.flush();
			}
		} else if (e.getSource() == accessB) { // 메인페이지 --> 대기방(login)
			String id = idT.getText();
			String pwss = pwT.getText();

			if (id.length() == 0 || pwss.length() == 0) {
				JOptionPane.showMessageDialog(this, "빈칸을 입력해주세요.");
			} else {
				String line = id + "%" + pwss;
				pw.println(Protocol.ENTERLOGIN + "|" + line);
				pw.flush();
			}
			idT.setText("");
			pwT.setText("");
		} else if (e.getSource() == RoomF.exitB) { // 대기실 --> 로그인페이지(로그아웃)
			RoomF.setVisible(false);
			this.setVisible(true);

			pw.println(Protocol.EXITWAITROOM + "|" + "message");
			pw.flush();
		} else if (e.getSource() == RoomF.sendB) { // 대기실 페이지 --> 메세지 전송
			String line = RoomF.chatT.getText();
			if (RoomF.chatT.getText().length() != 0) {
				pw.println(Protocol.SENDMESSAGE + "|" + line);
				pw.flush();
				RoomF.chatT.setText("");
			}
		} else if (e.getSource() == RoomF.makeB) {
			RoomF.setVisible(false);
			rMakeF.setVisible(true);
		} else if (e.getSource() == rMakeF.makeB) { // 방만들기 페이지 --> 방만들기 버튼
			String title = rMakeF.tf.getText();
			String rPassword = rMakeF.tf.getText();
			String users = (String) rMakeF.combo.getSelectedItem();
			int condition = rMakeF.cb.isSelected() ? 1 : 0;

			if (title.length() == 0) {
				JOptionPane.showMessageDialog(this, "제목을 입력해주세요.");
			} else if (condition == 0) {
				String line = "";
				line = line + (title + "%" + users + "%" + condition);
				pw.println(Protocol.ROOMMAKE + "|" + line);
				pw.flush();

				rMakeF.tf.setText("");
				rMakeF.pf.setText("");
				rMakeF.combo.setSelectedIndex(0);
				rMakeF.cb.setSelected(false);
			}
		} else if (e.getSource() == rMakeF.canB) { // 방만들기페이지 --> 취소버튼
			rMakeF.setVisible(false);
			RoomF.setVisible(true);
			rMakeF.tf.setText("");
			rMakeF.pf.setText("");
			rMakeF.combo.setSelectedIndex(0);
			rMakeF.cb.setSelected(false);
		} else if (e.getSource() == chattingF.exitB) { // 채팅방에서 나가기 버튼
			chattingF.setVisible(false);
			RoomF.setVisible(true);
			chattingF.model.removeAllElements();

			pw.println(Protocol.EXITCHATTINGROOM + "|" + "Message");
			pw.flush();

			chattingF.partList.setText("asd");
		}
	}

	public void run() {
		// 받는 쪽
		String[] line = null;
		while (true) {
			try {
				line = br.readLine().split("\\|");
				if (line == null) {
					br.close();
					pw.close();
					socket.close();

					System.exit(0);
				} else if (line[0].compareTo(Protocol.IDSEARCHCHECK_OK) == 0) { // 회원가입 ID 중복 안됨
					JOptionPane.showMessageDialog(this, "사용 가능");
					condition_Id = true;
				} else if (line[0].compareTo(Protocol.IDSEARCHCHECK_NO) == 0) { // 회원가입 ID 중복 됨
					JOptionPane.showMessageDialog(this, "사용 불가능");
					condition_Id = false;
				} else if (line[0].compareTo(Protocol.ENTERLOGIN_OK) == 0) { // 로그인 성공
					this.setVisible(false);
					RoomF.setVisible(true);
					RoomF.chatA.append(line[1] + line[2] + '\n');

					String text[] = line[3].split(":");
					String userlist = "";
					for (int i = 0; i < text.length; i++) {
						userlist += (text[i] + "\n");
					}
					RoomF.userA.setText(userlist);

				} else if (line[0].compareTo(Protocol.ENTERLOGIN_NO) == 0) { // 로그인 실패
					JOptionPane.showMessageDialog(this, line[1]);
					System.out.println("로그인실패");
				} else if (line[0].compareTo(Protocol.EXITWAITROOM) == 0) { // 로그아웃 [대기실 -> 로그인페이지]
					RoomF.chatA.append(line[1] + line[2] + '\n');

					String text[] = line[3].split(":");
					String userlist = "";
					for (int i = 0; i < text.length; i++) {
						userlist += (text[i] + "\n");
					}
					RoomF.userA.setText(userlist);

				} else if (line[0].compareTo(Protocol.SENDMASSAGE_ACK) == 0) { // 서버로 메세지 받음 [대기실]
					RoomF.chatA.append("[" + line[1] + "] :" + line[2] + '\n');

				} else if (line[0].compareTo(Protocol.ROOMMAKE_OK) == 0) {// 방만들어짐
					System.out.println("이거 되냐?");
					String[] roomList = line[1].split("-"); // 방 갯수
					for (int i = 0; i < roomList.length; i++) {
						System.out.print(roomList[i] + "/");
					}

					String roomListDetail[]; // 방세부
					System.out.println("RoomList size : " + roomList.length);

					RoomF.containPanelClear(); // 룸 프레임에 컨테이너를 비워주고
					for (int i = 0; i < roomList.length; i++) {

						RoomF.dp[i].init(); // 방리스트를 받은거로 다시 생성해주고
						roomListDetail = roomList[i].split("%");
						String userNumber = "";

						if (roomListDetail.length == 7) {
							userNumber = userNumber + (roomListDetail[6] + "/" + roomListDetail[2]);
							RoomF.dp[i].labelArray[1].setText(roomListDetail[0]); // 방번호
							RoomF.dp[i].labelArray[3].setText(userNumber); // 인원수
							RoomF.dp[i].labelArray[5].setText(roomListDetail[1]); // 방제목
							RoomF.dp[i].labelArray[8].setText("개설자 : " + roomListDetail[3]); // 개설자
						}
						System.out.println("userNumber : " + userNumber);

					}
					chattingF.area.setText("");
					chattingF.area1.setText("");
					rMakeF.setVisible(false); // 대기방 화면 끄고
					RoomF.setVisible(true);
				} else if (line[0].compareTo(Protocol.ROOMMAKE_OK1) == 0) { // 방만들어짐 (만든 당사자) // 입장
					rMakeF.setVisible(false); // 대기방 화면 끄고
					chattingF.area.setText("");
					chattingF.setVisible(true);
					chattingF.partList.setText(line[1] + "\n");

				} else if (line[0].compareTo(Protocol.ENTERROOM_OK1) == 0) { // 방입장 입장하는 당사자
					System.out.println("입장화면 변환");
					RoomF.setVisible(false);
					chattingF.area1.setText("");
					chattingF.area.setText("");
					chattingF.setVisible(true);

				} else if (line[0].compareTo(Protocol.ENTERROOM_USERLISTSEND) == 0) { // 채팅방 리스트 새로고침
					String roomMember[] = line[1].split("%");// 룸에 들어온사람들
					String lineList = "";
					for (int i = 0; i < roomMember.length; i++) {
						lineList += (roomMember[i] + "\n");
					}

					chattingF.partList.setText(lineList);
					chattingF.area1.append(line[2] + "\n");

					if (line.length == 4) {
						String fileList[] = line[3].split("%");
						chattingF.model.removeAllElements();
						for (int i = 0; i < fileList.length; i++) {
							chattingF.model.addElement(fileList[i]);
						}
					}

				} else if (line[0].compareTo(Protocol.CHATTINGSENDMESSAGE_OK) == 0) {
					chattingF.area1.append("[" + line[1] + "] :" + line[2] + "\n");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} // while
	}
}