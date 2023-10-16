package br.com.softblue.bluebank.infrastructure.api.security;

import java.time.Duration;
import java.time.Instant;
import java.util.UUID;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;

import br.com.softblue.bluebank.infrastructure.config.PropertiesConfig;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class JWTService {
	
	@Inject
	private PropertiesConfig props;
	
	private String key;
	private Algorithm alg;
	private String issuer;
	private JWTVerifier verifier;
	private Duration expirationInMinutes;
	
	@PostConstruct
	public void setup() {
		key = props.getSecret();
		alg = Algorithm.HMAC256(key);
		issuer = props.getIssuer();
		verifier = JWT.require(alg).withIssuer(issuer).build();
		expirationInMinutes = Duration.ofMinutes(props.getJwtExpirationInMinutes());
	}

	public String generate(String userId) {
		Instant now = Instant.now();
		return JWT
			.create()
			.withSubject(userId)
			.withIssuer(issuer)
			.withJWTId(UUID.randomUUID().toString())
			.withIssuedAt(now)
			.withExpiresAt(now.plus(expirationInMinutes))
			.withKeyId(key)
			.sign(alg);
	}
	
	public String verifyAndDecode(String jwt) {
		return verifier.verify(jwt).getSubject();
	}
}
