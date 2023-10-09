package br.com.softblue.bluebank.infrastructure.api.security;

import java.time.Duration;
import java.time.Instant;
import java.util.UUID;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class JWTService {
	
	private static final String KEY = "bluebank_key";
	private static final Algorithm ALG = Algorithm.HMAC256(KEY);
	private static final String ISSUER = "BlueBank";
	private static final JWTVerifier VERIFIER = JWT.require(ALG).withIssuer(ISSUER).build();

	public String generate(String userId) {
		Instant now = Instant.now();
		return JWT
			.create()
			.withSubject(userId)
			.withIssuer(ISSUER)
			.withJWTId(UUID.randomUUID().toString())
			.withIssuedAt(now)
			.withExpiresAt(now.plus(Duration.ofMinutes(5)))
			.withKeyId(KEY)
			.sign(ALG);
	}
	
	public String verifyAndDecode(String jwt) {
		return VERIFIER.verify(jwt).getSubject();
	}
}
