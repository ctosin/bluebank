package br.com.softblue.bluebank.infrastructure.api.dto;

import br.com.softblue.bluebank.domain.user.User;

public record UserDTO(
	String id,
	String name,
	String email,
	String cpf
) {
	
	public UserDTO(User user) {
		this (user.getId(), user.getName(), user.getEmail(), user.getCpf());
	}
}
