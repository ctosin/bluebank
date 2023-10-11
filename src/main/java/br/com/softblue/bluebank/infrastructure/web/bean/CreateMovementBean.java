package br.com.softblue.bluebank.infrastructure.web.bean;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;

@Named
@RequestScoped
public class CreateMovementBean {
	
	private Long accountId;

	public String create() {
		return null;
	}
	
	public Long getAccountId() {
		return accountId;
	}
	
	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}
}
