package bitedu.bipa.lesson3;

import java.sql.*;

public class GisaDAO {
	// DB관 작업 전용 클래스
	
	// 1번 문제
	public int selectQuiz1(String sql) {
		int stdNo = 0;
		Connection con;
		try {
			con = this.getConnection();
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				stdNo = rs.getInt(1);
			}
			rs.close();
			stmt.close();
			con.close();
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return stdNo;
	}
	
	public Connection getConnection() throws ClassNotFoundException, SQLException {
		Connection con = null;
		
		String jdbcURL = "jdbc:mysql://localhost:3306/bitedu";
		String driver = "com.mysql.cj.jdbc.Driver";
		String id = "root";
		String password = "1234";
		
		Class.forName(driver);
		con = DriverManager.getConnection(jdbcURL, id, password);
		
		return con;
	}
}
