package com.blubito.backend.config.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;

import java.util.Optional;

public class SecurityUtils {

    public static Optional<String> getCurrentLoggedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.isAuthenticated()) {
            return extractEmailFromAuthentication(authentication);
        }

        return Optional.empty();
    }

    public static Optional<String> extractEmailFromAuthentication(Authentication authentication) {
        Object principal = authentication.getPrincipal();

        if (principal instanceof Jwt) {
            Jwt jwtToken = (Jwt) principal;
            return Optional.ofNullable(jwtToken.getClaim("email"));
        }

        return Optional.empty();
    }
}
