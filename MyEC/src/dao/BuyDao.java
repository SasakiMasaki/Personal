package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Statement;

import beans.BuyDataBeans;

public class BuyDao {

	public static BuyDataBeans getBuyDataByBuyId(int buyId) throws SQLException{
		Connection con = DBManager.getConnection();

		try {
			PreparedStatement st = con.prepareStatement(
					"SELECT * FROM buy"
							+ " INNER JOIN delivery_method"
							+ " ON buy.delivery_method = delivery_method.id"
							+ " WHERE buy.id = ?");
			st.setInt(1, buyId);
			ResultSet rs = st.executeQuery();

			BuyDataBeans bdb = new BuyDataBeans();
			if(rs.next()) {
				bdb.setId(rs.getInt("id"));
				bdb.setUserId(rs.getInt("user_id"));
				bdb.setTotalPrice(rs.getInt("total_price"));
				bdb.setDeliveryMethod(rs.getInt("delivery_method"));
				bdb.setBuyDate(rs.getTimestamp("buy_date"));
				bdb.setDeliveryMethodName(rs.getString("name"));
				bdb.setDeliveryMethodPrice(rs.getInt("price"));
			}
			return bdb;
		}catch(SQLException e) {
			e.printStackTrace();
			throw new SQLException(e);
		}finally {
			if(con!=null) {
				con.close();
			}
		}
	}

	public static int insertBuyData(BuyDataBeans bdb) throws SQLException {
		Connection con = DBManager.getConnection();
		int buyId = 0;

		try {
			PreparedStatement st = con.prepareStatement("INSERT INTO buy(user_id, total_price, delivery_method, buy_date) VALUES(?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
			st.setInt(1, bdb.getUserId());
			st.setInt(2, bdb.getTotalPrice());
			st.setInt(3, bdb.getDeliveryMethod());
			st.setTimestamp(4, new Timestamp(System.currentTimeMillis()));
			st.executeUpdate();

			ResultSet rs = st.getGeneratedKeys();
			while(rs.next()) {
				buyId = rs.getInt(1);
			}
			System.out.println("inserting buy_data has been completed");
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			if(con!=null) {
				con.close();
			}
		}
		return buyId;
	}

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
			System.out.println("getting buy_list has been completed");
			return buyList;
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("getting buy_list has failed");
			throw new SQLException();
		}finally {
			if(con != null) {
				con.close();
			}
		}
	}
}
