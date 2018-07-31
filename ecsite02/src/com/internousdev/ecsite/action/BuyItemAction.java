package com.internousdev.ecsite.action;

import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

public class BuyItemAction extends ActionSupport implements SessionAware{
	private int itemQuantity;
	private String paymentsBy;
	public Map<String, Object> session;
	private String result = SUCCESS;

	public String execute() {
		session.put("item_quantity", itemQuantity);
		int itemPrice = Integer.parseInt(session.get("buyItem_price").toString());
		session.put("total_amount",  itemQuantity * itemPrice);

		if(paymentsBy.equals("1")) {
			paymentsBy = "現金払い";
			session.put("paymentsBy", paymentsBy);
		}else {
			paymentsBy = "クレジットカード";
			session.put("paymentsBy", paymentsBy);
		}
		return result;
	}

	public int getItemQuantity() {
		return itemQuantity;
	}

	public void setItemQuantity(int count) {
		this.itemQuantity = count;
	}

	public String getPaymentsBy() {
		return paymentsBy;
	}

	public void setPaymentsBy(String paymentsBy) {
		this.paymentsBy = paymentsBy;
	}

	public Map<String, Object> getSession(){
		return session;
	}

	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}
}

