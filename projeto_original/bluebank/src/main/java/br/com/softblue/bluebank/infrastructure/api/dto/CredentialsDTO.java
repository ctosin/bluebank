package br.com.softblue.bluebank.infrastructure.api.dto;

import jakarta.validation.constraints.NotEmpty;

public record CredentialsDTO(
	@NotEmpty(message = "E-mail não fornecido")
	String userId,

	@NotEmpty(message = "Senha não fornecida")
	String password
) {}
