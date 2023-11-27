package com.luizfiliperm.pms.infra.security.services.impl;

import com.luizfiliperm.pms.infra.security.services.JwtService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.security.Key;

@Service
public class JwtServiceImpl implements JwtService {

    private final String SECRET_KEY;

    public JwtServiceImpl(@Value("${api.security.token.secret}") String secretKey) {
        this.SECRET_KEY = secretKey;
    }

    private Key getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    @Override
    public String extractUsername(String jwtToken) {
        return null;
    }

    @Override
    public Claims getClaims(String jwtToken) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(jwtToken)
                .getBody();
    }
}
