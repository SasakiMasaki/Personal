package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import beans.BuyDetailDataBeans;
import beans.ItemDataBeans;

public class BuyDetailDao {

	public static ArrayList<ItemDataBeans> getItemDataBeansListByBuyId(int buyId) throws SQLException {
		Connection con = null;
		PreparedStatement st = null;
		try {
			con = DBManager.getConnection();

			st = con.prepareStatement(
					"SELECT item.id,"
					+ " item.name,"
					+ " item.price"
					+ " buy_detail.item_count"
					+ " FROM buy_detail"
					+ " INNER JOIN item"
					+ " ON buy_detail.item_id = item.id"
					+ " WHERE buy_detail.buy_id = ?");
			st.setInt(1, buyId);

			ResultSet rs = st.executeQuery();
			ArrayList<ItemDataBeans> buyDetailItemList = new ArrayList<ItemDataBeans>();

			while (rs.next()) {
				ItemDataBeans idb = new ItemDataBeans();
				idb.setId(rs.getInt("id"));
				idb.setName(rs.getString("name"));
				idb.setPrice(rs.getInt("price"));
				idb.setCount(rs.getInt("item_count"));

				buyDetailItemList.add(idb);
			}

			System.out.println("searching ItemDataBeansList by BuyID has been completed");
			return buyDetailItemList;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			throw new SQLException(e);
		} finally {
			if (con != null) {
				con.close();
			}
		}
	}

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
