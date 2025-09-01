package com.monolithic.chromamon.login.infrastructure.security;

import com.monolithic.chromamon.login.domain.model.User;
import com.monolithic.chromamon.login.domain.port.JwtService;
import com.monolithic.chromamon.shared.domain.exception.InvalidTokenException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;

/**
 * Implementation services for all JWT-related operations.
 */
@Service
@Slf4j
public class JwtServiceImpl implements JwtService {

   private final SecretKey secretKey;
   private final long jwtExpiration;

   public JwtServiceImpl(
      @Value("${application.security.jwt.secret-key:404E635266556A586E3272357538782F413F4428472B4B6250645367566B5970}") String secretKey,
      @Value("${application.security.jwt.expiration:86400000}") long jwtExpiration) {
      this.secretKey = Keys.hmacShaKeyFor(secretKey.getBytes());
      this.jwtExpiration = jwtExpiration;
   }

   @Override
   public String generateToken(User user, Set<String> permissions) {
      Map<String, Object> extraClaims = new HashMap<>();
      extraClaims.put("userId", user.id());
      extraClaims.put("email", user.email());
      extraClaims.put("firstName", user.firstName());
      extraClaims.put("lastName", user.lastName());
      extraClaims.put("role", user.role().name());
      extraClaims.put("permissions", permissions);

      return buildToken(extraClaims, user.username(), jwtExpiration);
   }

   @Override
   public String extractUsername(String token) {
      return extractClaim(token, Claims::getSubject);
   }

   @Override
   public Long extractUserId(String token) {
      return extractClaim(token, claims -> claims.get("userId", Long.class));
   }

   @Override
   public String extractRole(String token) {
      return extractClaim(token, claims -> claims.get("role", String.class));
   }

   @Override
   @SuppressWarnings("unchecked")
   public Set<String> extractPermissions(String token) {
      return extractClaim(token, claims -> (Set<String>) claims.get("permissions"));
   }

   @Override
   public boolean isTokenValid(String token, String username) {
      final String extractedUsername = extractUsername(token);
      return (extractedUsername.equals(username)) && !isTokenExpired(token);
   }

   @Override
   public boolean isTokenExpired(String token) {
      return extractExpiration(token).before(new Date());
   }

   private Date extractExpiration(String token) {
      return extractClaim(token, Claims::getExpiration);
   }

   private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
      final Claims claims = extractAllClaims(token);
      return claimsResolver.apply(claims);
   }

   private Claims extractAllClaims(String token) {
      try {
         return Jwts.parser()
            .verifyWith(secretKey)
            .build()
            .parseSignedClaims(token)
            .getPayload();
      } catch (JwtException e) {
         log.error("Error extracting jwtToken claims: {}", e.getMessage());
         throw new InvalidTokenException("Token invalid or expired: " + e.getMessage(), e);
      } catch (Exception e) {
         log.error("Internal Error: {}", e.getMessage());
         throw new RuntimeException("Invalid jwtToken", e);
      }
   }

   private String buildToken(Map<String, Object> extraClaims, String username, long expiration) {
      Date now = new Date();
      Date expiryDate = new Date(now.getTime() + expiration);

      return Jwts.builder()
         .header()
         .add("typ", "JWT")
         .and()
         .claims(extraClaims)
         .subject(username)
         .issuedAt(now)
         .expiration(expiryDate)
         .signWith(secretKey, Jwts.SIG.HS512)
         .compact();
   }
}