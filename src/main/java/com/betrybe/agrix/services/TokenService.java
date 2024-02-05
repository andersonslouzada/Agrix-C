package com.betrybe.agrix.services;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

/**
 * The type Token service.
 */
@Service
public class TokenService {
  private final Algorithm algorithm;

  /**
   * Instantiates a new Token service.
   *
   * @param secret the secret
   */
  public TokenService(@Value("${api.security.token.secret}") String secret) {
    this.algorithm = Algorithm.HMAC256(secret);
  }

  /**
   * Generate token string.
   *
   * @param person the person
   * @return the string
   */
  public String generateToken(UserDetails person) {
    return JWT.create()
        .withSubject(person.getUsername())
        .withExpiresAt(generateExpiration())
        .sign(algorithm);
  }

  /**
   * Validate token string.
   *
   * @param token the token
   * @return the string
   */
  public String validateToken(String token) {
    return JWT.require(algorithm)
        .build()
        .verify(token)
        .getSubject();
  }

  private Instant generateExpiration() {
    return Instant.now()
        .plus(2, ChronoUnit.HOURS);
  }
}
