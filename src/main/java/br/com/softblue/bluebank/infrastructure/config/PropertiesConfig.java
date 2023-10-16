package br.com.softblue.bluebank.infrastructure.config;

import java.io.IOException;
import java.io.InputStream;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.json.Json;
import jakarta.json.JsonObject;

@ApplicationScoped
public class PropertiesConfig {
	
	private JsonObject jsonObj;
	
	@PostConstruct
	public void setup() {
		 
		try (InputStream in = PropertiesConfig.class.getResourceAsStream("/config.json")) {
			jsonObj = Json.createReader(in).readObject();
			
		} catch (IOException e) {
			throw new RuntimeException("Error loading config file", e);
		}
	}
	
	public String getSecret() {
		return jsonObj.getString("jwtSecret");
	}
	
	public String getIssuer() {
		return jsonObj.getString("issuer");
	}
	
	public int getJwtExpirationInMinutes() {
		return jsonObj.getInt("jwtExpirationInMinutes");
	}
}
