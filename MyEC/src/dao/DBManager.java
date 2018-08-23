package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBManager {
	private static String url = "jdbc:mysql://localhost:3306/my_ec?useUnicode=true&characterEncoding=utf8";
	private static String user = "root";
	private static String password = "password";

	public static Connection getConnection() {
		Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(url, user, password);
		}catch(SQLException|ClassNotFoundException e) {
			e.printStackTrace();
		}
		return con;
	}
}
