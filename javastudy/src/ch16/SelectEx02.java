package ch16;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class SelectEx02 {

	public static void main(String[] args) {
//		OracleDriver o = new OracleDriver();
//		�ٸ� ���ÿ��� �ʿ��ϸ� �� new �ؾ��Ѵ�. 

		try {
			final String SQL = "select id, name, email, password from users";
			// OJDBC ������ �ش� ����̹��� �ε��϶�� �޴����� ����(�������̽�)
			Class.forName("oracle.jdbc.driver.OracleDriver"); // heap�� ����Ŭ ����̹� new�ؼ� ���(stream �����ϱ� ���ؼ�) but �ּ� ��
			// ��Ʈ�� ����(��Ÿ���̽��� ����� ��Ʈ��)
			Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "ssar", "bitc5600");
			// ���۴ޱ�(?�� ����ϰ� ���ش�), ������ ������ ������.
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			// ���ۿ� ����(ResultSet(Ŀ��)�� ���Ϲ���-ù��° Ŀ���� ���Ϲ���)
			ResultSet rs = pstmt.executeQuery();
			List<Users> users = new ArrayList<>(); // ������������ �θ�� ����
			while (rs.next()) { // rs�� java ������Ʈ�� �ٲپ� ��.
				Users user = new Users(rs.getInt("id"),
						rs.getString("name"),
						rs.getString("email"),
						rs.getString("password")
						);
				users.add(user);
			}
			for (Users user : users) {
				System.out.println(user.getId()); // �÷��� ����(������)
				System.out.println(user.getName());
				System.out.println(user.getEmail());
				System.out.println(user.getPassword());
				System.out.println();
			}


		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
