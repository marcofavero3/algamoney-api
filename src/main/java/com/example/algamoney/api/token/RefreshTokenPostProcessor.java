package com.example.algamoney.api.token;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.security.oauth2.core.endpoint.OAuth2AccessTokenResponse;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.security.oauth2.core.OAuth2RefreshToken;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

@ControllerAdvice
public class RefreshTokenPostProcessor implements ResponseBodyAdvice<OAuth2AccessTokenResponse> {

    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return returnType.getMethod().getName().equals("postAccessToken");
    }

    @Override
    public OAuth2AccessTokenResponse beforeBodyWrite(OAuth2AccessTokenResponse body, MethodParameter returnType,
                                                     MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType,
                                                     ServerHttpRequest request, ServerHttpResponse response) {

        HttpServletRequest req = ((ServletServerHttpRequest) request).getServletRequest();
        HttpServletResponse resp = ((ServletServerHttpResponse) response).getServletResponse();

        OAuth2RefreshToken refreshToken = body.getRefreshToken();
        if (refreshToken != null) {
            adicionarRefreshTokenNoCookie(refreshToken.getTokenValue(), req, resp);
            body = removerRefreshTokenDoBody(body);
        }

        return body;
    }

    private OAuth2AccessTokenResponse removerRefreshTokenDoBody(OAuth2AccessTokenResponse body) {
        return OAuth2AccessTokenResponse.withToken(body.getAccessToken().getTokenValue())
                .tokenType(body.getAccessToken().getTokenType())
                .expiresIn(body.getAccessToken().getExpiresAt().getEpochSecond() - body.getAccessToken().getIssuedAt().getEpochSecond())
                .scopes(body.getAccessToken().getScopes())
                .additionalParameters(body.getAdditionalParameters())
                .build();
    }

    private void adicionarRefreshTokenNoCookie(String refreshToken, HttpServletRequest req, HttpServletResponse resp) {
        Cookie refreshTokenCookie = new Cookie("refreshToken", refreshToken);
        refreshTokenCookie.setHttpOnly(true);
        refreshTokenCookie.setSecure(false); // TODO: Mudar para true em produção
        refreshTokenCookie.setPath(req.getContextPath() + "/oauth/token");
        refreshTokenCookie.setMaxAge(2592000);
        resp.addCookie(refreshTokenCookie);
    }
}
