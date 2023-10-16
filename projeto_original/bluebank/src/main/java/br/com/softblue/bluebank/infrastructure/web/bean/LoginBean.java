package br.com.softblue.bluebank.infrastructure.web.bean;

import br.com.softblue.bluebank.application.service.AuthService;
import br.com.softblue.bluebank.domain.user.User;
import br.com.softblue.bluebank.infrastructure.web.security.LoggedUser;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.context.ExternalContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.validation.constraints.NotBlank;

@Named
@RequestScoped
public class LoginBean {
	
	@Inject
	private AuthService authService;
	
	@Inject
	private LoggedUser loggedUser;
	
	@Inject
	private ExternalContext externalContext;
	
	@NotBlank(message = "E-mail was not provided")
	private String email;
	
	@NotBlank(message = "Password was not provided")
	private String password;
	
	private String message;
	
	public String login() {
		User user = authService.loginByEmail(email, password).orElse(null);
		
		if (user != null) {
			loggedUser.setId(user.getId());
			loggedUser.setName(user.getName());
			return "secured/home?faces-redirect=true";
		}

		message = "Login inválido";
		return null;
	}
	
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
	
	public String getMessage() {
		return message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
}
