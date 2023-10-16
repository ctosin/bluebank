package br.com.softblue.bluebank.infrastructure.api.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class SaveMovementDTO {
	
	@NotEmpty(message = "O tipo de movimento não foi fornecido")
	private String type;
	
	@NotNull(message = "Valor não foi fornecido")
	@DecimalMin(value = "0.01", message = "Valor não pode ser zero ou negativo")
	private BigDecimal amount;
	
	@NotEmpty(message = "Descrição não pode ser vazia")
	@Size(max = 80, message = "Descrição é muito grande")
	private String description;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
