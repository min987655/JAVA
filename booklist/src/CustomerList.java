
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;



public class CustomerList 
{
	//1.�������
	private Connection con; // �������
	private Statement stmt;
	private ResultSet rs;
	
	//1.�������
//	private int custid;
//	private String name;
//	private String address;
//	private String phone;
	
	//2.�迭
	private int custid2[]; // ��! -> ���۷���(����)
	private String name2[];
	private String address2[];
	private String phone2[];
	
	//3.��ü
//	class Customer2{
//		private int custid;
//		private String name;
//		private String address;
//		private String phone;
//	}	
	Customer cs;
	
	//4.��ü �迭
	Customer cs2[];
	
	//������
	public CustomerList()
	{
		//1.���� �ʱ�ȭ
//		custid 	= 0;
//		name 	= "";
//		address = "";
//		phone	= "";
		
		//2.�迭 �ʱ�ȭ
		custid2  = new int[5];
		name2	 = new String[5];
		address2 = new String[5];
		phone2	 = new String[5];
		
		//3.��ü �ʱ�ȭ
		cs = new Customer();
		
		//4.��ü �迭 �ʱ�ȭ
		cs2 = new Customer[5];//��ü �ƴ�! �迭�� ��(����)
		//4-1.��ü �迭�� ���������, 
		//�ݵ�� ��ü�迭�ȿ� �� "��ü"���Ҹ� ������ �ȴ�.
		for(int i=0; i<5; i++)
			cs2[i]  = new Customer();//5���� ��ü ����
	}
	
	

	public void getConnection() 
	{
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String userid =  "c##madang"; //c##�߰�
		String pwd = "c##madang"; //c##�߰�
	   
		try 
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("����̹� �ε� ����");
		}
		catch (ClassNotFoundException e) 
		{
		   e.printStackTrace();
		}
		
		try 
		{
		   System.out.println("�����ͺ��̽� ���� �غ� .....");
		   con = DriverManager.getConnection(url,userid,pwd);
		   System.out.println("�����ͺ��̽� ���� ����");
		}
		catch (SQLException e) 
		{
		   e.printStackTrace();
		}
	}

	public void getCustomer_DB() //�ϴ� ���� ����?
	{ 
		String query = "SELECT custid, name, address, phone  FROM customer";
		try 
		{
			stmt = con.createStatement(); //2
			rs = stmt.executeQuery(query); //3
			System.out.println("BOOK ID \tBOOK NAME \t\tPUBLISHER \t\t\tPRICE");
			
			int index=0;
			while (rs.next ()) 
			{
				//1.������ ���
//				custid 	= rs.getInt(1);
//				name 	= rs.getString(2);
//				address	= rs.getString(3);
//				phone	= rs.getString(4);
				
//				print1();
				
				//2.�迭�� ���
//				custid2[index] 	= rs.getInt(1);
//				name2[index] 	= rs.getString(2);
//				address2[index]	= rs.getString(3);
//				phone2[index]	= rs.getString(4);
//				
//				index++;
				
				//3.��ü�� ��� //���� ���� ���� ����!
//				cs.custid 	= rs.getInt(1);
//				cs.name 	= rs.getString(2);
//				cs.address	= rs.getString(3);
//				cs.phone	= rs.getString(4);
				
//				cs.setCustid(rs.getInt(1));
//				cs.setName(rs.getString(2));
//				cs.setAddress(rs.getString(3));
//				cs.setPhone(rs.getString(4));
//				
//				print3();
				
				//4.��ü �迭�� ���,  ��������� ���� ���� ����!
//				cs2[0].custid 	= rs.getInt(1);
//				cs2[0].name 	= rs.getString(2);
//				cs2[0].address	= rs.getString(3);
//				cs2[0].phone	= rs.getString(4);
				
				cs2[index].setCustid(rs.getInt(1));
				cs2[index].setName(rs.getString(2));
				cs2[index].setAddress(rs.getString(3));
				cs2[index].setPhone(rs.getString(4));
				
				index++;
			}
			con.close();
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}
	
//	private void print1()
//	{
//		System.out.print(custid + "\t");
//		System.out.print(name+ "\t");
//		System.out.print(address+ "\t");
//		System.out.println(phone+ "\t");
//	}
	
//	void print2()
//	{
//		for(int index=0; index<5; ++index)
//		{
//			System.out.print(custid2[index] + "\t");
//			System.out.print(name2[index]+ "\t");
//			System.out.print(address2[index]+ "\t");
//			System.out.println(phone2[index]+ "\t");
//		}
//	}
	
	void print3()
	{
		//���� ���� ���� ����!
//		System.out.print(cs.custid + "\t");
//		System.out.print(cs.name+ "\t");
//		System.out.print(cs.address+ "\t");
//		System.out.println(cs.phone+ "\t");
		
		//�׷��� ��� �޼ҵ带 ���ؼ� ����
		System.out.print(cs.getCustid() + "\t");
		System.out.print(cs.getName()+ "\t");
		System.out.print(cs.getAddress()+ "\t");
		System.out.println(cs.getPhone()+ "\t");
	}
	
	void print4()
	{
		//���� ���� ���� ����!
//		for(int i=0; i<5;i++)
//		{
//			System.out.print(cs2[i].custid + "\t");
//			System.out.print(cs2[i].name+ "\t");
//			System.out.print(cs2[i].address+ "\t");
//			System.out.println(cs2[i].phone+ "\t");
//		}
		
		//�׷��� ��� �޼ҵ带 ���ؼ� ����
		//���� ���� ���� ����!
		for(int i=0; i<5;i++)
		{
			System.out.print(cs2[i].getCustid() + "\t");
			System.out.print(cs2[i].getName()+ "\t");
			System.out.print(cs2[i].getAddress()+ "\t");
			System.out.println(cs2[i].getPhone()+ "\t");
		}
		
	}

}




	
	

