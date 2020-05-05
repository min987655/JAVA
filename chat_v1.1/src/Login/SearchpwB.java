package Login;

import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class SearchpwB extends JFrame implements ActionListener{
	private JLabel nameL,idL;
	JButton newpwB,okpwB,joinB,cancleB;
	private JTextField newpwT,okpwT,nameT,idT;
	
	public SearchpwB() {
	setTitle("비밀번호 찾기");
	idL=new JLabel("아이디");
	idT=new JTextField(10);
	JPanel p1=new JPanel();
	p1.add(idL);
	p1.add(idT);
	
	nameL=new JLabel("이름");
	nameT=new JTextField(5);
	
	JPanel p2=new JPanel();
	p2.add(nameL);
	p2.add(nameT);
	
	joinB=new JButton("확인");
	cancleB=new JButton("취소");
	newpwB=new JButton("비밀번호 재설정");
	JPanel p5=new JPanel();
	p5.add(joinB);
	p5.add(cancleB);
	p5.add(newpwB);
	 
	JPanel p= new JPanel(new GridLayout(4,1));
	p.add(p1);
	p.add(p2);
	
	Container contentPane=this.getContentPane();
	contentPane.add("Center",p);
	contentPane.add("South",p5);
	
//	setVisible(true);
	setResizable(false);
	setBounds(400, 200,500,500);
	setDefaultCloseOperation(EXIT_ON_CLOSE);
	
	
	newpwB.addActionListener(this);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==newpwB) {
			new Newpassword();
			System.out.println("ddd");
		}
	}
	
}