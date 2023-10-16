package br.com.softblue.bluebank.infrastructure.web.converter;

import static jakarta.faces.application.FacesMessage.SEVERITY_ERROR;

import java.math.BigDecimal;

import br.com.softblue.bluebank.infrastructure.util.NumberUtils;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.faces.convert.ConverterException;
import jakarta.faces.convert.FacesConverter;

@FacesConverter("bigDecimalConverter")
public class BigDecimalConverter implements Converter<BigDecimal> {

	@Override
	public BigDecimal getAsObject(FacesContext context, UIComponent component, String value) {
		if (value == null) {
			return null;
		}
		
		try {
			return new BigDecimal(value.replace(',',  '.'));
		} catch (NumberFormatException e) {
			FacesMessage message = new FacesMessage("Valor inválido");
			message.setSeverity(SEVERITY_ERROR);
			throw new ConverterException(message);
		}
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, BigDecimal value) {
		if (value == null) {
			return null;
		}
		
		return NumberUtils.formatNumber(value);
	}
}
