package br.com.softblue.bluebank.infrastructure.config;

import java.io.IOException;
import java.io.InputStream;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.json.Json;
import jakarta.json.JsonObject;

@ApplicationScoped
public class PropertiesConfig {
	private static final String CONFIG_FILE = "/config.json";
	private static final String PROP_JWT_SECRET = "jwtSecret";
	private static final String PROP_JWT_ISSUER = "jwtIssuer";
	private static final String PROP_JWT_EXPIRATION_IN_MINUTES = "jwtExpirationInMinutes";
	
	private static final int DEFAULT_JWT_DURATION_IN_MINUTES = 5;

	private JsonObject props;
	
	@PostConstruct
	public void setup() {
		try (InputStream in = PropertiesConfig.class.getResourceAsStream(CONFIG_FILE)) {
			props = Json.createReader(in).readObject();
		} catch (IOException e) {
			throw new RuntimeException("Error loading config from file " + CONFIG_FILE, e);
		}
	}
	
	public String getJWTSecret() {
		return props.getString(PROP_JWT_SECRET);
	}
	
	public String getJWTIssuer() {
		return props.getString(PROP_JWT_ISSUER);
	}
	
	public int getJWTExpirationInMinutes() {
		return props.getInt(PROP_JWT_EXPIRATION_IN_MINUTES, DEFAULT_JWT_DURATION_IN_MINUTES);
	}
}
