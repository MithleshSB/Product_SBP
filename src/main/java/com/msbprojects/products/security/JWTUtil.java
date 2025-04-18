package com.msbprojects.products.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.function.Function;

@Component
public class JWTUtil {
    //private String secretKey = "your-secret-key"; // Use a secure key! or can generate random more secure
    private static String secret_key;

    JWTUtil() {
        SecureRandom secureRandom = new SecureRandom();
        byte[] key = new byte[32]; // 256 bits
        secureRandom.nextBytes(key);
        secret_key = Base64.getEncoder().encodeToString(key);
    }

    public String generateToken(String username, List<String> roles) {
        return Jwts.builder()
                .setSubject(username)
                .claim("roles", roles)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 2)) // 2 minute expiration
                .signWith(SignatureAlgorithm.HS256, getSignedKey())
                .compact();
    }

    private Key getSignedKey() {
        byte[] keyByte = Decoders.BASE64.decode(secret_key);
        return Keys.hmacShaKeyFor(keyByte);
    }

    public boolean validateToken(String token, String username) {
        return username.equals(extractUsername(token)) && !isTokenExpired(token);
    }

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public List<String> extractRole(String token) {
        return extractClaim(token, claims -> claims.get("roles", List.class));
    }

    private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = Jwts.parserBuilder()
                .setSigningKey(getSignedKey())
                .build()
                .parseClaimsJws(token) //jws ,not jst
                .getBody();
        return claimsResolver.apply(claims);
    }


}
