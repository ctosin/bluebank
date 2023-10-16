package br.com.softblue.bluebank.infrastructure.api.converter;

import java.time.LocalDate;

import jakarta.ws.rs.ext.ParamConverter;

public class LocalDateConverter implements ParamConverter<LocalDate> {

   public LocalDate fromString(String value) {
      return LocalDate.parse(value);
   }

   public String toString(LocalDate value) {
	   return value.toString();
   }
}
