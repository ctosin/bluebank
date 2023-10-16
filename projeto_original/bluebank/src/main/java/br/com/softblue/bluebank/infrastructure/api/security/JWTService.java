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
	private static final String CLAIM_USER_ID = "userId";
	
	@Inject
	private PropertiesConfig properties;
	
	private Algorithm algorithm;
	
	private JWTVerifier verifier;
	
	@PostConstruct
	public void setup() {
		algorithm = Algorithm.HMAC256(properties.getJWTSecret());
		verifier = JWT.require(algorithm).withIssuer(properties.getJWTIssuer()).build();
	}
	
	public AppJWT verifyAndDecode(String token) {
        return new AppJWT(
        	verifier
        		.verify(token)
        		.getClaim(CLAIM_USER_ID)
        		.asString()
        );
    }
	
	public String generate(String userId) {
		Instant now = Instant.now();
        return JWT
        	.create()
        	.withIssuer(properties.getJWTIssuer())
        	.withClaim(CLAIM_USER_ID, userId)
        	.withJWTId(UUID.randomUUID().toString())
        	.withIssuedAt(now)
        	.withExpiresAt(now.plus(Duration.ofMinutes(properties.getJWTExpirationInMinutes())))
        	.withNotBefore(now)
        	.withKeyId(properties.getJWTSecret())
        	.sign(algorithm);
    }
}
