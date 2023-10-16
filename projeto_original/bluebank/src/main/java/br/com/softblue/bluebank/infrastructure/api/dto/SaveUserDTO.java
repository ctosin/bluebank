package br.com.softblue.bluebank.infrastructure.api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class SaveUserDTO {
	
	@NotBlank(message = "Nome não pode ser vazio")
	@Size(max = 80, message = "Nome é muito longo")
	private String name;
	
	@NotBlank(message = "Senha não pode ser vazia")
	@Size(max = 15, message = "Senha é muito longa")
	private String password;
	
	@NotBlank(message = "E-mail não pode ser vazio")
	@Size(max = 80, message = "E-mail é muito longo")
	@Pattern(regexp = "^(.+)@(.+)$", message = "E-mail não tem o formato correto")
	private String email;
	
	@NotBlank(message = "CPF não pode ser vazio")
	@Pattern(regexp = "[0-9]+", message = "CPF só pode conter números")
	private String cpf;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
}

