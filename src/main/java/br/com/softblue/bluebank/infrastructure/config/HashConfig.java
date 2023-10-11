package br.com.softblue.bluebank.infrastructure.config;

import br.com.softblue.bluebank.application.HashGenerator;
import br.com.softblue.bluebank.application.MD5HashGenerator;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;

@ApplicationScoped
public class HashConfig {

	@Produces
	@ApplicationScoped
	public HashGenerator create() {
		//return new SHAHashGenerator();
		return new MD5HashGenerator();
	}
}
