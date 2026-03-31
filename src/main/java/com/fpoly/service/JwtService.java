package com.fpoly.service;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.Map;

@Service
public class JwtService {
    
    // Tạo chữ ký số
    private Key getSigningKey() {
        String secret = "0123456789.0123456789.0123456789"; // Chuỗi secret key (>= 32 byte cho HS256)
        return Keys.hmacShaKeyFor(secret.getBytes());
    }

    // Tạo JWT
    public String create(UserDetails user, int expirySeconds) {
        long now = System.currentTimeMillis();
        return Jwts.builder()
                .setClaims(Map.of("name", "Poly"))
                .setSubject(user.getUsername())
                .setIssuedAt(new Date(now))
                .setExpiration(new Date(now + 1000L * expirySeconds))
                .signWith(this.getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    // Bóc tách phần body từ JWT
    public Claims getBody(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(this.getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    // Xác minh thời gian hiệu lực
    public boolean validate(Claims claims) {
        return claims.getExpiration().after(new Date());
    }
}