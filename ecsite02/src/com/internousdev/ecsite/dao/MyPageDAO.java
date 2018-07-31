package com.internousdev.ecsite.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.internousdev.ecsite.dto.MyPageDTO;
import com.internousdev.ecsite.util.DBConnector;


public class MyPageDAO {
	private DBConnector db = new DBConnector();
	private Connection conn = db.getConnection();

	public ArrayList<MyPageDTO> getMyPageUserInfo(String item_transaction_id, String user_master_id) throws SQLException{
		ArrayList<MyPageDTO> myPageDTO = new ArrayList<MyPageDTO>();
		String sql = "SELECT "
								+ "ubit.id, iit.item_name, ubit.total_price, ubit.total_count,"
								+ "ubit.pay, ubit.insert_date"
						+ " FROM "
								+ "user_buy_item_transaction ubit"
						+ " LEFT JOIN "
								+ "item_info_transaction iit"
						+ " ON "
								+ "ubit.item_transaction_id = iit.id"
						+ " WHERE "
								+ "ubit.item_transaction_id=? AND ubit.user_master_id=?"
						+ " ORDER BY "
								+ "insert_date DESC";

		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, item_transaction_id);

			ps.setString(2, user_master_id);
			ResultSet rs = ps.executeQuery();

			while(rs.next()) {
				MyPageDTO mpDTO = new MyPageDTO();
				mpDTO.setItemName(rs.getString("item_name"));
				mpDTO.setTotalPrice(rs.getString("total_price"));
				mpDTO.setTotalCount(rs.getString("total_count"));
				mpDTO.setPayment(rs.getString("pay"));
				mpDTO.setInsert_date(rs.getString("insert_date"));
				myPageDTO.add(mpDTO); //Listに追加
			}
		}catch(Exception e) {
				e.printStackTrace();
		}finally {
			conn.close();
		}
		return myPageDTO; //Listを戻す
	}


	public int buyItemHistoryDelete(String item_transaction_id, String user_master_id) throws SQLException{
		DBConnector db = new DBConnector();
		Connection conn = db.getConnection();

		String sql="DELETE FROM user_buy_item_transaction WHERE item_transaction_id=? AND user_master_id=?";
		PreparedStatement ps;
		int result = 0;

		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, item_transaction_id);
			ps.setString(2, user_master_id);

			result = ps.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			conn.close();
		}
		return result;

	}
}
