package bitedu.bipa.lesson4;

import java.sql.*;
import java.util.*;
import java.io.*;

public class ConnectionManager {
	
	public static void closeConnection(ResultSet rs, Statement stmt, Connection con) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			rs = null;
		}
		if (stmt != null) {
			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			stmt = null;
		}
		if (con != null) {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			con = null;
		}
	}
	
	public static Connection getConnection() {
		Connection con = null;
		// 환경정보는 값이 바뀌기 때문에 밖에서 결정되어야 함
		
		// Properties class 이용해서 값 설정
		Properties properties = new Properties();
		try {
			properties.load(new FileReader("C:\\Users\\jungw\\eclipse-workspace\\Basic\\data\\db\\properties"));
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		/*for (int i = 0; i < properties.size(); i++) {
			list.add(properties.values().toString());
		}*/
		
		// Stream 이용하여 값 설정
		
		String jdbcURL = properties.getProperty("jdbcURL");
		String driver = properties.getProperty("driver");
		String id = properties.getProperty("id");
		String password = properties.getProperty("password");
		
		System.out.println(jdbcURL);
		System.out.println(password);
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(jdbcURL, id, password);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return con;
	}
	
}
