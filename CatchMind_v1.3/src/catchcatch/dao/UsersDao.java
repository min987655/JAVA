package catchcatch.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import catchcatch.db.DBConnection;
import catchcatch.db.DBUtils;
import catchcatch.models.Users;

// 자바와 디비의 거점
public class UsersDao {

	private final static String TAG = "UsersDao : ";

	public UsersDao() {
	}

	private static UsersDao instance = new UsersDao();

	public static UsersDao getInstance() {
		return instance;
	}

	public int 가입(Users users) {

		final String SQL = "INSERT INTO users(id, userName, password) VALUES(users_seq.nextval, ?, ?)";
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			// 1. 스트림 연결
			conn = DBConnection.getConnection();
			// 2. 버퍼달기
			pstmt = conn.prepareStatement(SQL);
			// 3. 물음표 완성
			pstmt.setString(1, users.getUserName());
			pstmt.setString(2, users.getPassword());
			// 4. 쿼리 전송(flush + commit)
			int result = pstmt.executeUpdate();
			return result;
		} catch (Exception e) {
			System.out.println(TAG + "추가오류 : " + e.getMessage());
		} finally {
			DBUtils.close(conn, pstmt);
		}
		return -1;
	}

	public static int 확인(String userName) {

		final String SQL = "SELECT userName FROM users WHERE username = ?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DBConnection.getConnection();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, userName);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				return 0;
			} else {
				return 1;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}

	public int 로그인(String userName, String password) {
		final String SQL = "SELECT userName FROM users WHERE username = ? AND password = ?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DBConnection.getConnection();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, userName);
			pstmt.setString(2, password);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				return 1;
			} else {
				return 0;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}
	
}
