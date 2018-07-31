package com.internousdev.ecsite.action;

import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.internousdev.ecsite.dao.BuyItemDAO;
import com.internousdev.ecsite.dao.SignInDAO;
import com.internousdev.ecsite.dto.BuyItemDTO;
import com.internousdev.ecsite.dto.SignInDTO;
import com.opensymphony.xwork2.ActionSupport;


public class LoginAction extends ActionSupport implements SessionAware{
	private String loginUserId;
	private String loginPassword;
	private Map<String, Object> session;
	SignInDAO loginDAO = new SignInDAO();
	SignInDTO loginDTO = new SignInDTO();
	BuyItemDAO buyItemDAO = new BuyItemDAO();

	public String execute() {
		String result = ERROR;
		loginDTO = loginDAO.getLoginUserInfo(loginUserId, loginPassword);

		//データベースに接続し、引数で渡した文字列に一致するデータがあればDTOインスタンスのプロパティにセットされ、
		//LoginDTOのLoginFlgプロパティがtrueに初期化される
		session.put("loginUser", loginDTO);

		if(((SignInDTO)session.get("loginUser")).getLoginFlg()) {
			//sessionに格納されているloginUserキーのvalue(LoginDTO)をキャストしてgetLoginFlg()の戻り値を条件にする
			//上記のdaoの処理でLoginFlgがtrueになっていればif文が実行される
			result = SUCCESS;
			BuyItemDTO buyItemDTO = buyItemDAO.getBuyItemInfo();
			//ログインが成功したので、buyItemDTOに商品情報を格納

			session.put("login_user_id", loginDTO.getLoginId());
			session.put("id", buyItemDTO.getId());
			session.put("buyItem_name", buyItemDTO.getItemName());
			session.put("buyItem_price", buyItemDTO.getItemPrice());
		}
		return result;
	}

	//getter
	public String getLoginUserId() {
		return loginUserId;
	}
	public String getLoginPassword() {
		return loginPassword;
	}
	public Map<String, Object> getSession(){
		return session;
	}

	//setter
	public void setLoginUserId(String loginUserId) {
		this.loginUserId = loginUserId;
	}
	public void setLoginPassword(String loginPassword) {
		this.loginPassword = loginPassword;
	}
	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}


}
