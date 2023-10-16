package br.com.softblue.bluebank.infrastructure.web.security;

import java.io.Serializable;

import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;

@Named
@SessionScoped
public class LoggedUser implements Serializable {
	
	private String id;
	private String name;
	
	public boolean isLogged() {
		return id != null;
	}
	
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
}
