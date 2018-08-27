package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import beans.BuyDataBeans;

public class BuyDao {

	public static List<BuyDataBeans> getBuyDataListByUserId(int userId) throws SQLException{
		List<BuyDataBeans> buyList = new ArrayList<BuyDataBeans>();
		Connection con = DBManager.getConnection();

		try {
			PreparedStatement st = con.prepareStatement("SELECT * FROM buy WHERE user_id = ?");
			st.setInt(1, userId);
			ResultSet rs = st.executeQuery();
			while(rs.next()) {
				BuyDataBeans buy = new BuyDataBeans();
				buy.setId(rs.getInt("id"));
				buy.setUserId(rs.getInt("user_id"));
				buy.setTotalPrice(rs.getInt("total_price"));
				buy.setDeliveryMethod(rs.getInt("delivery_method"));
				buy.setBuyDate(rs.getDate("buy_date"));
				buyList.add(buy);
			}
			return buyList;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SQLException();
		}finally {
			if(con != null) {
				con.close();
			}
		}
	}
}
