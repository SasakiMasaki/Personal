package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import beans.ItemDataBeans;
import beans.SearchIndexBeans;

public class ItemDao {

	public static int getNumberOfResult(String keyword) throws SQLException {
		Connection con = DBManager.getConnection();
		int resultNum = 0;
		try {
			PreparedStatement st = con.prepareStatement("SELECT id FROM item WHERE name LIKE ?");
			st.setString(1,"%" + keyword + "%");
			ResultSet rs = st.executeQuery();
			while(rs.next()){
				resultNum++;
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			if(con != null) {
				con.close();
			}
		}
		return resultNum;
	}

	public static List<ItemDataBeans> getItemListResultByKeyword(SearchIndexBeans sib) throws SQLException{
		List<ItemDataBeans> itemList = new ArrayList<ItemDataBeans>();
		Connection con = DBManager.getConnection();
		try {
			PreparedStatement st = con.prepareStatement(
					"SELECT *"
					+ " FROM (SELECT @i := @i + 1 AS row, result.* FROM (SELECT @i := 0) AS dummy, (SELECT * FROM item WHERE name LIKE ? ORDER BY name)result)part"
					+ " WHERE row BETWEEN ? AND ?");
			st.setString(1, "%" + sib.getKeyword() + "%");
			st.setInt(2, (sib.getIndex() - 1) * sib.getDisplayNum() + 1);
			st.setInt(3, sib.getIndex() * sib.getDisplayNum());
			ResultSet rs = st.executeQuery();
			while(rs.next()) {
				ItemDataBeans idb = new ItemDataBeans();
				idb.setId(rs.getInt("id"));
				idb.setName(rs.getString("name"));
				idb.setDetail(rs.getString("detail"));
				idb.setPrice(rs.getInt("price"));
				idb.setFileName(rs.getString("file_name"));
				itemList.add(idb);
			}
			System.out.println("getting item_list has been completed");
			return itemList;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SQLException();
		}finally {
			if(con != null) {
				con.close();
			}
		}
	}
	public static int getLastId() throws SQLException{
		Connection con = DBManager.getConnection();
		int id = 0;
		try {
			PreparedStatement st = con.prepareStatement("SELECT MAX(id) FROM item");
			ResultSet rs = st.executeQuery();
			while(rs.next()) {
				id = rs.getInt("MAX(id)");
				System.out.println("last_id has been found, id : " + id);
			}
			st = con.prepareStatement("ALTER TABLE item AUTO_INCREMENT = ?");
			st.setInt(1, id + 1);
			st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			if(con != null) {
				con.close();
			}
		}
		return id;
	}

	public static void updateItem(ItemDataBeans idb) throws SQLException{
		Connection con = DBManager.getConnection();
		try {
			PreparedStatement st = con.prepareStatement("UPDATE item SET name = ?, detail = ?, price = ?, file_name = ? WHERE id = ?");
			st.setString(1, idb.getName());
			st.setString(2, idb.getDetail());
			st.setInt(3, idb.getPrice());
			st.setString(4, idb.getFileName());
			st.setInt(5, idb.getId());
			st.executeUpdate();
			System.out.println("updating item has been completed");
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			if(con != null) {
				con.close();
			}
		}
	}
	public static void addItem(ItemDataBeans idb) throws SQLException{
		Connection con = DBManager.getConnection();
		try {
			PreparedStatement st = con.prepareStatement("INSERT INTO item(name,detail,price,file_name) VALUES(?,?,?,?)");
			st.setString(1, idb.getName());
			st.setString(2, idb.getDetail());
			st.setInt(3, idb.getPrice());
			st.setString(4, idb.getFileName());
			st.executeUpdate();
			System.out.println("adding item has been completed");
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			if(con != null) {
				con.close();
			}
		}
	}

	public static void deleteItem(int itemId) throws SQLException{
		Connection con = DBManager.getConnection();
		try {
			PreparedStatement st = con.prepareStatement("DELETE FROM item WHERE id = ?");
			st.setInt(1, itemId);
			st.executeUpdate();
			System.out.println("item has been deleted");

		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			if(con!=null) {
				con.close();
			}
		}
	}

	public static boolean isOverlapName(String name) throws SQLException{
		Connection con = DBManager.getConnection();
		boolean isOverlap = false;

		try {
			PreparedStatement st = con.prepareStatement("SELECT name FROM item WHERE name = ?");
			st.setString(1, name);
			ResultSet rs = st.executeQuery();
			System.out.println("searching item_name has been completed");

			if(rs.next()) {
				isOverlap = true;
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			if(con != null) {
				con.close();
			}
		}
		System.out.println("overlap check has been completed");
		return isOverlap;
	}

	public static ItemDataBeans getItemById(int i) throws SQLException{
		ItemDataBeans idb = new ItemDataBeans();
		Connection con = DBManager.getConnection();
		try {
			PreparedStatement st = con.prepareStatement("SELECT * FROM item WHERE id = ?");
			st.setInt(1, i);
			ResultSet rs = st.executeQuery();
			while(rs.next()) {
				idb.setId(rs.getInt("id"));
				idb.setName(rs.getString("name"));
				idb.setDetail(rs.getString("detail"));
				idb.setPrice(rs.getInt("price"));
				idb.setFileName(rs.getString("file_name"));
			}
			System.out.println("getting item_list has been completed");
			return idb;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SQLException();
		}finally {
			if(con != null) {
				con.close();
			}
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
				idb.setName(rs.getString("name"));
				idb.setDetail(rs.getString("detail"));
				idb.setPrice(rs.getInt("price"));
				idb.setFileName(rs.getString("file_name"));
				itemList.add(idb);
			}
			System.out.println("getting item_list has been completed");
			return itemList;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SQLException();
		}finally {
			if(con != null) {
				con.close();
			}
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
				idb.setName(rs.getString("name"));
				idb.setDetail(rs.getString("detail"));
				idb.setPrice(rs.getInt("price"));
				idb.setFileName(rs.getString("file_name"));
				itemList.add(idb);
			}
			System.out.println("getting item_list has been completed");
			return itemList;
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
