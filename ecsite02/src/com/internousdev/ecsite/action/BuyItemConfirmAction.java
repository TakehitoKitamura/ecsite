package com.internousdev.ecsite.action;

import java.sql.SQLException;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.internousdev.ecsite.dao.BuyItemCompleteDAO;
import com.opensymphony.xwork2.ActionSupport;

public class BuyItemConfirmAction extends ActionSupport implements SessionAware{
	private Map<String, Object> session;
	private BuyItemCompleteDAO bicDAO = new BuyItemCompleteDAO();

	public String execute() throws SQLException{
		bicDAO.buyItemInfo(
				session.get("id").toString(),
				session.get("login_user_id").toString(),
				session.get("total_amount").toString(),
				session.get("item_quantity").toString(),
				session.get("paymentsBy").toString());

		String result = SUCCESS;
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
