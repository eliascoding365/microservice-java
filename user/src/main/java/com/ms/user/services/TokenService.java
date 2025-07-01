package com.ms.user.services;

import com.ms.user.models.UserModel;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import io.jsonwebtoken.security.Keys; 
import java.security.Key;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;

@Service
public class TokenService {

  @Value("${api.jwt.secret}")
  private String secret;

  public String generateToken(UserModel userModel) {
    try {

      byte[] secretBytes = secret.getBytes();

      Key signingKey = Keys.hmacShaKeyFor(secretBytes);

      String token = Jwts.builder()
          .setIssuer("user-ms-api")
          .setSubject(userModel.getEmail())
          .setIssuedAt(new Date())
          .setExpiration(generateExpirationDate())
          .signWith(signingKey)
          .compact();

      return token;
    } catch (Exception e) {
      throw new RuntimeException("Erro ao gerar token JWT", e);
    }

  }

  private Date generateExpirationDate() {
    return Date.from(Instant.now().plusSeconds(7200));
  }
}
