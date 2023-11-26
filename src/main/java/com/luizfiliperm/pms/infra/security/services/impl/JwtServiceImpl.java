package com.luizfiliperm.pms.infra.security.services.impl;

import com.luizfiliperm.pms.infra.security.services.JwtService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class JwtServiceImpl implements JwtService {

    @Value("${api.security.token.secret}")
    private String key;

    public String getSigningKey() {
        return key;
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
