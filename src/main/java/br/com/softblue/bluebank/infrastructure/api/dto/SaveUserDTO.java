package br.com.softblue.bluebank.infrastructure.api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class SaveUserDTO {

	@NotBlank(message = "Nome não fornecido")
	@Size(max = 80, message = "Nome é muito grande")
	private String name;
	
	@NotBlank(message = "E-mail não fornecido")
	@Size(max = 80, message = "E-mail é muito grande")
	@Pattern(regexp = "^(.+)@(.+)$", message = "E-mail está fora do padrão")
	private String email;
	
	@NotBlank(message = "Senha não fornecida")
	@Size(max = 15, message = "Senha é muito grande")
	private String password;
	
	@NotBlank
	@Pattern(regexp = "[0-9]{11}", message = "CPF está fora do padrão")
	private String cpf;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	
	
}
