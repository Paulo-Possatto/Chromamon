package com.monolithic.chromamon.login.domain.port;

import com.monolithic.chromamon.login.domain.model.User;

import java.util.Set;

public interface JwtService {

   String generateToken(User user, Set<String> permissions);

   String extractUsername(String token);

   Long extractUserId(String token);

   String extractRole(String token);

   Set<String> extractPermissions(String token);

   boolean isTokenValid(String token, String username);

   boolean isTokenExpired(String token);
}
