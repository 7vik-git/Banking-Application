package com.gevernova.banking_application.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {

    @Value("${jwt.secret}")
    private String secretKey;

    // ⏳ Token expiration time (1 hour)
    private static final long EXPIRATION_TIME = 1000 * 60 * 60;

    // 🔑 Return a signing key
    private Key getSigningKey() {
        return Keys.hmacShaKeyFor(secretKey.getBytes());
    }

    // 🧾 Generate JWT Token using username (from UserDetails)
    public String generateToken(UserDetails userDetails) {
        return Jwts.builder()
                .setSubject(userDetails.getUsername())             // username is subject
                .setIssuedAt(new Date())                           // token creation time
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))  // expiry
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)   // sign with key
                .compact(); // final string token
    }

    // 🔍 Extract username from token
    public String extractUsername(String token) {
        return extractAllClaims(token).getSubject();
    }

    // ✅ Validate the token
    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    // 🕒 Check token expiration
    private boolean isTokenExpired(String token) {
        return extractAllClaims(token).getExpiration().before(new Date());
    }

    // 📜 Extract all claims (payload)
    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}

