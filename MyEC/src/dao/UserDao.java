package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import beans.UserDataBeans;

public class UserDao {

	public static void addUser(UserDataBeans udb) throws SQLException{
		Connection con = null;
		PreparedStatement st = null;

		try {
			con = DBManager.getConnection();
			st = con.prepareStatement("INSERT INTO user(name,address,email,password) VALUES(?,?,?,?)");
			st.setString(1, udb.getName());
			st.setString(2, udb.getAddress());
			st.setString(3, udb.getEmail());
			st.setString(4, udb.getPassword());
			st.executeUpdate();
			System.out.println("adding user has succeeded");
		}catch(SQLException e) {
			System.out.println(e.getMessage());
			throw new SQLException(e);
		}finally {
			if(con != null) {
				con.close();
			}
		}
	}

	public static boolean isOverlapEmail(String email) throws SQLException{
		Connection con = null;
		PreparedStatement st = null;
		boolean isOverlap = false;

		try {
			con = DBManager.getConnection();
			st = con.prepareStatement("SELECT email FROM user WHERE email = ?");
			st.setString(1, email);
			ResultSet rs = st.executeQuery();
			System.out.println("searching email has been completed");

			if(rs.next()) {
				isOverlap = true;
			}
		}catch(SQLException e) {
			System.out.println(e.getMessage());
			throw new SQLException(e);
		}finally {
			if(con != null) {
				con.close();
			}
		}
		System.out.println("overlap check has been completed");
		return isOverlap;
	}
}
