package address.gui;

import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import address.model.GroupType;

public class DetailFrame extends JFrame {

	private JFrame detailFrame = this;
	private JFrame mainFrame;
	private int memberId; // mainFrame에서 넘어온 member의 id 값
	private Container backgroundPanel;
	private JLabel laName, laPhone, laAddress, laGroup;
	private JTextField tfName, tfPhone, tfAddress;
	private JComboBox<GroupType> cbGroup;
	private JButton updateButton, deleteButton;
	
	public DetailFrame(JFrame mainFrame, int memberId) {
		this.mainFrame = mainFrame;
		initObject();
		initData();
		initDesign();
		initListener();
		setVisible(true);
	}
	
	private void initObject() {
		backgroundPanel = getContentPane();
		laName = new JLabel("이름");
		laPhone = new JLabel("전화번호");
		laAddress = new JLabel("주소");
		laGroup = new JLabel("그룹");
		tfName = new JTextField(20);
		tfPhone = new JTextField(20);
		tfAddress = new JTextField(20);
		cbGroup = new JComboBox<>(GroupType.values()); // .valuse : 배열
		updateButton = new JButton("수정하기");
		deleteButton = new JButton("삭제하기");
		
	}
	
	private void initData() {
		
	}
	
	private void initDesign() {
		setTitle("주소록 상세보기");
		setSize(300, 300);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Exit 하면 메인이 같이 꺼짐
		backgroundPanel.setLayout(new GridLayout(5,2));
		
		backgroundPanel.add(laName);
		backgroundPanel.add(tfName);
		backgroundPanel.add(laPhone);
		backgroundPanel.add(tfPhone);
		backgroundPanel.add(laAddress);
		backgroundPanel.add(tfAddress);
		backgroundPanel.add(laGroup);
		backgroundPanel.add(cbGroup);
		backgroundPanel.add(updateButton);
		backgroundPanel.add(deleteButton);		
	}
	
	private void initListener() {
		updateButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) { // 익명 클래스
				detailFrame.dispose();
			}
		});
		deleteButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) { // 익명 클래스
				detailFrame.dispose();
			}
		});
	}
	
}











