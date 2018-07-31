package com.internousdev.ecsite.action;

import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.internousdev.ecsite.dao.BuyItemDAO;
import com.internousdev.ecsite.dto.BuyItemDTO;
import com.opensymphony.xwork2.ActionSupport;

public class HomeAction extends ActionSupport implements SessionAware{
	public Map<String, Object> session;

	public String execute() {
		String result = ERROR;

		if(session.containsKey("login_user_id")) {//ログイン済みの場合は次の画面で商品情報を表示しなければならないので、ここでDTOに情報を格納する。
			BuyItemDAO biDAO = new BuyItemDAO();
			BuyItemDTO biDTO = biDAO.getBuyItemInfo();
			session.put("id", biDTO.getId());
			session.put("buyItem_name", biDTO.getItemName());
			session.put("buyItem_price", biDTO.getItemPrice());
			result = SUCCESS;
		}
		return result;
	}

	public Map<String, Object> getSession(){
		return session;
	}

	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}
}
