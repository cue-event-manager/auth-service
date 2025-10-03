package cue.edu.co.jwttokenprovider;


import cue.edu.co.jwttokenprovider.constants.JwtClaim;
import cue.edu.co.model.auth.gateways.TokenProvider;
import cue.edu.co.model.user.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtTokenProviderAdapter implements TokenProvider {
    private final Key key;
    private final long expiration;

    public JwtTokenProviderAdapter(
            @Value("${app.jwt.secret}") String secret,
            @Value("${app.jwt.expiration}") long expiration) {
        this.key = Keys.hmacShaKeyFor(secret.getBytes());
        this.expiration = expiration;
    }

    @Override
    public String generate(User user) {
        return Jwts.builder()
                .subject(user.getEmail())
                .claim(JwtClaim.ROLE.getClaim(), user.getRole().getName())
                .claim(JwtClaim.USER_ID.getClaim(), user.getId())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(key)
                .compact();
    }
}
