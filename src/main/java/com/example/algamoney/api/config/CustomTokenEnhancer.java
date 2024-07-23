package com.example.algamoney.api.config;

import org.springframework.security.oauth2.server.authorization.token.JwtEncodingContext;
import org.springframework.security.oauth2.server.authorization.token.OAuth2TokenCustomizer;
import org.springframework.security.oauth2.core.OAuth2RefreshToken;
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
            OAuth2RefreshToken refreshToken = new OAuth2RefreshToken(refreshTokenValue, Instant.now(), Instant.now().plus(30, ChronoUnit.DAYS));
            context.getClaims().claim("refresh_token", refreshToken.getTokenValue());
            System.out.println("Refresh Token added: " + refreshToken.getTokenValue());
        }
    }
}
