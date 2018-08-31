package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import beans.UserDataBeans;
import myec.Controllor;

public class UserDao {

	public static void addUser(UserDataBeans udb) throws SQLException{
		Connection con = DBManager.getConnection();

		try {
			PreparedStatement st = con.prepareStatement("INSERT INTO user(name,address,email,password) VALUES(?,?,?,?)");
			st.setString(1, udb.getName());
			st.setString(2, udb.getAddress());
			st.setString(3, udb.getEmail());
			st.setString(4, Controllor.hashStr(udb.getPassword()));
			st.executeUpdate();
			System.out.println("adding user has been completed");
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}finally {
			if(con != null) {
				con.close();
			}
		}
	}

	public static void updateUser(UserDataBeans udb) throws SQLException{
		Connection con = DBManager.getConnection();

		try {
			PreparedStatement st = con.prepareStatement("UPDATE user SET name = ?, address = ?, email = ?, password = ? WHERE id = ?");
			st.setString(1, udb.getName());
			st.setString(2, udb.getAddress());
			st.setString(3, udb.getEmail());
			st.setString(4, Controllor.hashStr(udb.getPassword()));
			st.setInt(5, udb.getId());
			st.executeUpdate();
			System.out.println("updateng user has been completed");
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}finally {
			if(con != null) {
				con.close();
			}
		}
	}

	public static UserDataBeans findUserById(int id) throws SQLException{
		Connection con = DBManager.getConnection();
		UserDataBeans udb = new UserDataBeans();

		try {
			PreparedStatement st = con.prepareStatement("SELECT * FROM user WHERE id = ?");
			st.setInt(1, id);
			ResultSet rs = st.executeQuery();
			while(rs.next()) {
				udb.setId(id);
				udb.setName(rs.getString("name"));
				udb.setAddress(rs.getString("address"));
				udb.setEmail(rs.getString("email"));
				udb.setPassword(rs.getString("password"));
			}
			System.out.println("finding user by id has been completed");
			return udb;
		}catch(SQLException e) {
			e.printStackTrace();
			throw new SQLException();
		}finally {
			if(con != null) {
				con.close();
			}
		}
	}

	public static int findIdByEmail(String email, String password) throws SQLException{
		Connection con = DBManager.getConnection();
		int id = 0;
		try {
			PreparedStatement st = con.prepareStatement("SELECT id FROM user WHERE email = ? AND password = ?");
			st.setString(1, email);
			st.setString(2, Controllor.hashStr(password));
			ResultSet rs = st.executeQuery();
			System.out.println("searching user by email has been completed");
			if (rs.next()) {
				id = rs.getInt("id");
			}
			return id;
		}catch(Exception e) {
			e.printStackTrace();
			throw new SQLException();
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
