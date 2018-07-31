package com.internousdev.ecsite.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.internousdev.ecsite.dto.SignInDTO;
import com.internousdev.ecsite.util.DBConnector;

public class SignInDAO {
	DBConnector db = new DBConnector();
	Connection conn =db.getConnection();
	private SignInDTO loginDTO = new SignInDTO(); //初級では下のメソッドの中に定義してた。フィールド変数にした理由。

	public SignInDTO getLoginUserInfo(String loginUserId, String loginPassword) {//DBから読み込んだデータをDTOインスタンスに格納
		String sql = "SELECT * FROM login_user_transaction WHERE login_id=? AND login_pass=?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, loginUserId);
			ps.setString(2, loginPassword);

			ResultSet rs = ps.executeQuery();

			if(rs.next()) { //データが返ってきていればDTOプロパティにセット
				loginDTO.setLoginId(rs.getString("login_id"));
				loginDTO.setLoginPassword(rs.getString("login_pass"));
				loginDTO.setUserName(rs.getString("user_name"));

				if(!(rs.getString("login_id").equals(null))){
						loginDTO.setLoginFlg(true);
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return loginDTO;
	}

	public SignInDTO getLoginDTO() {//なんでgetterいれてんの
		return loginDTO;
	}
}
