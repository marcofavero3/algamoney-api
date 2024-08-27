package com.example.algamoney.api.token;

import org.springframework.security.oauth2.server.authorization.token.JwtEncodingContext;
import org.springframework.security.oauth2.server.authorization.token.OAuth2TokenCustomizer;
import org.springframework.stereotype.Component;

@Component
public class CustomTokenEnhancer implements OAuth2TokenCustomizer<JwtEncodingContext> {

    @Override
    public void customize(JwtEncodingContext context) {
        if ("client_credentials".equals(context.getAuthorizationGrantType().getValue())) {
            // Adicionar informações adicionais ao token, se necessário
            context.getClaims().claim("custom_claim", "custom_value");
            System.out.println("Custom claim added to the token.");
        }
    }
}
