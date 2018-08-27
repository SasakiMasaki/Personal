package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import beans.ItemDataBeans;

public class ItemDao {

	public static ItemDataBeans getItemById(int i) throws SQLException{
		ItemDataBeans idb = new ItemDataBeans();
		Connection con = DBManager.getConnection();
		try {
			PreparedStatement st = con.prepareStatement("SELECT * FROM item WHERE id = ?");
			st.setInt(1, i);
			ResultSet rs = st.executeQuery();
			while(rs.next()) {
				idb.setId(rs.getInt("id"));
				idb.setName(rs.getString("id"));
				idb.setDetail(rs.getString("id"));
				idb.setPrice(rs.getInt("id"));
				idb.setFileName(rs.getString("id"));
			}
			System.out.println("getting item_list has been completed");
			return idb;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SQLException();
		}
	}

	public static List<ItemDataBeans> getAllItemList() throws SQLException{
		List<ItemDataBeans> itemList = new ArrayList<ItemDataBeans>();
		Connection con = DBManager.getConnection();
		try {
			PreparedStatement st = con.prepareStatement("SELECT * FROM item");
			ResultSet rs = st.executeQuery();
			while(rs.next()) {
				ItemDataBeans idb = new ItemDataBeans();
				idb.setId(rs.getInt("id"));
				idb.setName(rs.getString("id"));
				idb.setDetail(rs.getString("id"));
				idb.setPrice(rs.getInt("id"));
				idb.setFileName(rs.getString("id"));
				itemList.add(idb);
			}
			System.out.println("getting item_list has been completed");
			return itemList;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SQLException();
		}
	}

	public static List<ItemDataBeans> getItemListByKeyword(String keyword) throws SQLException{
		List<ItemDataBeans> itemList = new ArrayList<ItemDataBeans>();
		Connection con = DBManager.getConnection();
		try {
			PreparedStatement st = con.prepareStatement("SELECT * FROM item WHERE name LIKE ?");
			st.setString(1, "%" + keyword + "%");
			ResultSet rs = st.executeQuery();
			while(rs.next()) {
				ItemDataBeans idb = new ItemDataBeans();
				idb.setId(rs.getInt("id"));
				idb.setName(rs.getString("id"));
				idb.setDetail(rs.getString("id"));
				idb.setPrice(rs.getInt("id"));
				idb.setFileName(rs.getString("id"));
				itemList.add(idb);
			}
			System.out.println("getting item_list has been completed");
			return itemList;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SQLException();
		}
	}
}
