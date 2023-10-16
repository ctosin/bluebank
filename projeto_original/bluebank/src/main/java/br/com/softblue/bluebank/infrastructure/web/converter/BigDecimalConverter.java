package br.com.softblue.bluebank.infrastructure.web.converter;

import static jakarta.faces.application.FacesMessage.SEVERITY_ERROR;
import static java.util.Objects.isNull;

import java.math.BigDecimal;
import java.util.Optional;

import br.com.softblue.bluebank.util.NumberUtils;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.faces.convert.ConverterException;
import jakarta.faces.convert.FacesConverter;

@FacesConverter(value="bigDecimalConverter")
public class BigDecimalConverter implements Converter<BigDecimal> {

	@Override
	public BigDecimal getAsObject(FacesContext context, UIComponent component, String value) {
		if (isNull(value)) {
			return null;
		}
		
		try {
			return new BigDecimal(value.replace(',', '.'));
		} catch (NumberFormatException e) {
			throw newConverterException("Valor inválido");
		}
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, BigDecimal value) {
		return Optional
			.ofNullable(value)
			.map(NumberUtils::getFormattedNumber)
			.orElse(null);
	}
	
	private ConverterException newConverterException(String errorMsg) {
		FacesMessage facesMessage = new FacesMessage(errorMsg);
		facesMessage.setSeverity(SEVERITY_ERROR);
		return new ConverterException(facesMessage);
	}
}
