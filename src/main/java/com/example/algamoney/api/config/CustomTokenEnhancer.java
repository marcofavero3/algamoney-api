package com.example.algamoney.api.config;

import org.springframework.security.oauth2.server.authorization.token.JwtEncodingContext;
import org.springframework.security.oauth2.server.authorization.token.OAuth2TokenCustomizer;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

@Component
public class CustomTokenEnhancer implements OAuth2TokenCustomizer<JwtEncodingContext> {

    @Override
    public void customize(JwtEncodingContext context) {
        if ("client_credentials".equals(context.getAuthorizationGrantType().getValue())) {
            String refreshTokenValue = UUID.randomUUID().toString();
            context.getClaims().claim("refresh_token", refreshTokenValue);
            System.out.println("Refresh Token added: " + refreshTokenValue);
        }
    }
}
