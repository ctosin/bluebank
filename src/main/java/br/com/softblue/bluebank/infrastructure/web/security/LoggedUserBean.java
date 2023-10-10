package br.com.softblue.bluebank.infrastructure.web.security;

import java.io.Serializable;

import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;

@SuppressWarnings("serial")
@Named
@SessionScoped
public class LoggedUserBean implements Serializable {

	private String id;
	
	private String name;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public boolean isLogged() {
		return id != null;
	}
}
