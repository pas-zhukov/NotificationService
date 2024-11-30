package ru.pas_zhukov.notificationservice.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.pas_zhukov.notificationservice.model.UserRole;

import javax.crypto.SecretKey;

@Component
public class JwtTokenManager {

    private final SecretKey key;

    public JwtTokenManager(
            @Value("${jwt.secret-key}") String keyString
    ) {
        this.key = Keys.hmacShaKeyFor(keyString.getBytes());
    }

    public String getLoginFromToken(String jwtToken) {
        return Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(jwtToken)
                .getPayload()
                .getSubject();
    }

    public UserRole getUserRoleFromToken(String jwtToken) {
        return Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(jwtToken)
                .getPayload()
                .get("authority", UserRole.class);
    }
}