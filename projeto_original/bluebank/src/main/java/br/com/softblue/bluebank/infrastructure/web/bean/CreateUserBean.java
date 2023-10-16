package br.com.softblue.bluebank.infrastructure.web.bean;

import static java.util.Objects.isNull;

import br.com.softblue.bluebank.application.service.UserService;
import br.com.softblue.bluebank.infrastructure.api.dto.SaveUserDTO;
import br.com.softblue.bluebank.infrastructure.api.exception.RequestException;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.validation.Valid;

@Named
@RequestScoped
public class CreateUserBean {
	
	@Inject
	private UserService userService;

	@Valid
	private SaveUserDTO user;
	
	private String message;
	
	public String create() {
		try {
			userService.create(user);
		} catch (RequestException e) {
			message = e.getMessage();
			return null;
		}
		return "user_created?faces-redirect=true";
	}
	
	public SaveUserDTO getUser() {
		if (isNull(user)) {
			user = new SaveUserDTO();
		}
		return user;
	}
	
	public void setUser(SaveUserDTO user) {
		this.user = user;
	}
	
	public String getMessage() {
		return message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
}
