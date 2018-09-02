package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import beans.DeliveryMethodDataBeans;

public class DeliveryMethodDao {

	public static List<DeliveryMethodDataBeans> getAllDeliveryMethod() throws SQLException{
		Connection con = DBManager.getConnection();
		List<DeliveryMethodDataBeans> dmList = new ArrayList<DeliveryMethodDataBeans>();

		try {
			PreparedStatement st = con.prepareStatement("SELECT * FROM delivery_method");
			ResultSet rs = st.executeQuery();
			while(rs.next()){
				DeliveryMethodDataBeans dm = new DeliveryMethodDataBeans();
				dm.setId(rs.getInt("id"));
				dm.setName(rs.getString("name"));
				dm.setPrice(rs.getInt("price"));
				dmList.add(dm);
			}
			System.out.println("searching delivery_method_list has been completed");
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			if(con!=null) {
				con.close();
			}
		}
		return dmList;
	}

	public static DeliveryMethodDataBeans getDeliveryMethodById(int dmId) throws SQLException {
		Connection con = DBManager.getConnection();
		DeliveryMethodDataBeans dm = new DeliveryMethodDataBeans();

		try {
			PreparedStatement st = con.prepareStatement("SELECT * FROM delivery_method WHERE id = ?");
			st.setInt(1, dmId);
			ResultSet rs = st.executeQuery();
			while(rs.next()){
				dm.setId(rs.getInt("id"));
				dm.setName(rs.getString("name"));
				dm.setPrice(rs.getInt("price"));
			}
			System.out.println("seraching delivery_method has been completed");
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			if(con!=null) {
				con.close();
			}
		}
		return dm;
	}
}
