package com.luizfiliperm.pms.infra.security.services;

import io.jsonwebtoken.Claims;

public interface JwtService {
    String extractUsername(String jwtToken);

    Claims getClaims(String jwtToken);
}
