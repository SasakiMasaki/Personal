package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBManager {
	private static String url = "";
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
