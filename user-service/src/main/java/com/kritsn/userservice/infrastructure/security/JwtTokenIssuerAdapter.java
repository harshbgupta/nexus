package com.kritsn.userservice.infrastructure.security;

import com.kritsn.userservice.domain.model.User;
import com.kritsn.userservice.domain.port.out.TokenIssuerPort;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.time.Instant;
import java.util.Date;

@Component
public class JwtTokenIssuerAdapter implements TokenIssuerPort {

    private final SecretKey signingKey;
    private final Duration expiry;

    public JwtTokenIssuerAdapter(
            @Value("${nexus.jwt.secret}") String secret,
            @Value("${nexus.jwt.expiry-minutes:60}") long expiryMinutes) {
        this.signingKey = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
        this.expiry = Duration.ofMinutes(expiryMinutes);
    }

    @Override
    public String issue(User user) {
        Instant now = Instant.now();
        return Jwts.builder()
                .subject(user.getId().toString())
                .claim("email", user.getEmail())
                .issuedAt(Date.from(now))
                .expiration(Date.from(now.plus(expiry)))
                .signWith(signingKey)
                .compact();
    }
}
