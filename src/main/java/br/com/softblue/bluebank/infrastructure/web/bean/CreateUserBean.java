package br.com.softblue.bluebank.infrastructure.web.bean;

import br.com.softblue.bluebank.application.UserService;
import br.com.softblue.bluebank.domain.exception.RequestException;
import br.com.softblue.bluebank.infrastructure.api.dto.SaveUserDTO;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.validation.Valid;

@Named
@RequestScoped
public class CreateUserBean {

	@Valid
	private SaveUserDTO user;
	
	@Inject
	private UserService userService;
	
	private String message;
	
	@PostConstruct
	public void setup() {
		user = new SaveUserDTO();
	}
	
	public String create() {
		try {
			userService.createUser(user);
		} catch (RequestException e) {
			message = e.getMessage();
			return null;
		}
		
		//TODO Lógica para cadastrar
		return "user_created?faces-redirect=true";
	}

	public SaveUserDTO getUser() {
//		if (user == null) {
//			user = new SaveUserDTO();
//		}
		return user;
	}

	public void setUser(SaveUserDTO user) {
		this.user = user;
	}
	
	public String getMessage() {
		return message;
	}
}
