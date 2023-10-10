package br.com.softblue.bluebank.infrastructure.web.bean;

import br.com.softblue.bluebank.application.AuthService;
import br.com.softblue.bluebank.domain.user.User;
import br.com.softblue.bluebank.infrastructure.web.security.LoggedUserBean;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@Named
@RequestScoped
public class LoginBean {

	private String email;
	
	private String password;
	
	private String message;
	
	@Inject
	private AuthService authService;
	
	@Inject
	private LoggedUserBean loggedUserBean;
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String login() {
		User user = authService.loginByEmail(email, password);
		
		if (user == null) {
			message = "Login inválido";
			return null;
		}
		
		loggedUserBean.setId(user.getId());
		loggedUserBean.setName(user.getName());
		
		return "secured/home?faces-redirect=true";
	}
	
	public String getMessage() {
		return message;
	}
}
