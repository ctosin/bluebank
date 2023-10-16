package br.com.softblue.bluebank.infrastructure.api.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotEmpty;

public class SaveMovementDTO {

	private String type;
	
	@DecimalMin(value = "0.01", message = "Valor não pode ser zero ou negativo")
	private BigDecimal amount;
	
	@NotEmpty(message = "Descrição não fornecida")
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

	@Override
	public String toString() {
		return "SaveMovementDTO [type=" + type + ", amount=" + amount + ", description=" + description + "]";
	}
}
