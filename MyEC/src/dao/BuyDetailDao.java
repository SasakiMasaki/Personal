package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import beans.BuyDetailDataBeans;

public class BuyDetailDao {

	public static void insertBuyDetail(BuyDetailDataBeans bddb) throws SQLException {
		Connection con = DBManager.getConnection();

		try {
			PreparedStatement st = con.prepareStatement("INSERT INTO buy_detail(buy_id, item_id, item_count) VALUES(?,?,?)");
			st.setInt(1, bddb.getBuyId());
			st.setInt(2, bddb.getItemId());
			st.setInt(3, bddb.getItemCount());
			st.executeUpdate();
			System.out.println("inserting buy_detail has been completed");
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			if(con!=null) {
				con.close();
			}
		}
	}
}
