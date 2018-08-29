package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FileDao {

	public static String getLocation(String name) throws SQLException {
		Connection con = DBManager.getConnection();
		String location = "";
		try {
			PreparedStatement st = con.prepareStatement("SELECT location FROM file_path WHERE name = ?");
			st.setString(1, name);
			ResultSet rs = st.executeQuery();
			while(rs.next()) {
				location = rs.getString("location");
			}
			System.out.println("getting location has been completed : " + location);
			return location;
		}catch(SQLException e) {
			e.printStackTrace();
			throw new SQLException();
		}finally {
			if(con!=null) {
				con.close();
			}
		}
	}
}
