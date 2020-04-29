package ch16;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class InsertEx01 {

	public static void main(String[] args) {
//		OracleDriver o = new OracleDriver();
//		�ٸ� ���ÿ��� �ʿ��ϸ� �� new �ؾ��Ѵ�. 

		try {
			final String SQL = "insert into users(id,name,email,password) values(?,?,?,?)"; //?�� ������ �Ľ��� ����. �����ǰ����� ������
			// OJDBC ������ �ش� ����̹��� �ε��϶�� �޴����� ����(�������̽�)
			Class.forName("oracle.jdbc.driver.OracleDriver"); // heap�� ����Ŭ ����̹� new�ؼ� ���(stream �����ϱ� ���ؼ�) but �ּ� ��
			// ��Ʈ�� ����(��Ÿ���̽��� ����� ��Ʈ��)
			Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "ssar", "bitc5600");
			// ���۴ޱ�(?�� ����ϰ� ���ش�)
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, 4);
			pstmt.setString(2, "����");
			pstmt.setString(3, "jj@nate.com");
			pstmt.setString(4, "1234");
			// ���ۿ� ����(commit/auto flush ����)
			pstmt.executeUpdate();
			System.out.println("�μ�Ʈ �Ϸ�");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
