package com.team600.moalarm.gateway.common.config.filter;

import com.team600.moalarm.gateway.common.config.filter.JwtDecodeFilter.Config;
import com.team600.moalarm.gateway.common.config.provider.TokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.GatewayFilterFactory;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.security.crypto.encrypt.TextEncryptor;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import com.team600.moalarm.gateway.common.config.vo.JwtDecryptResult;

@Component
public class JwtDecodeFilter implements GatewayFilterFactory<Config> {
    @Autowired
    @Qualifier("JwtSubjectEncryptor")
    private TextEncryptor textEncryptor;
    private final TokenProvider tokenProvider;

    private static final String BEARER = "Bearer ";
    private static final String MEMBER_ID_HEADER_NAME = "Member-Id";

    public JwtDecodeFilter(TokenProvider tokenProvider) {
        this.tokenProvider = tokenProvider;
    }

    public static class Config {
    }
    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            String token = getToken(exchange);
            if (!tokenProvider.validateAccessToken(token)) {
                throw new RuntimeException("토큰 유효성 검사 실패");
            }
            JwtDecryptResult jwtDecryptResult = tokenProvider.decryptJwt(token);
            addAuthorizationHeaders(exchange.getRequest(), jwtDecryptResult.getSubject());

            return chain.filter(exchange);
        };
    }
    @Override
    public Class<Config> getConfigClass() {
        return Config.class;
    }

    private String getToken(ServerWebExchange exchange) {
        return exchange.getRequest().getHeaders().get("Authorization").get(0)
                .substring(BEARER.length());
    }


    private void addAuthorizationHeaders(ServerHttpRequest request, String subject) {
        request.mutate().header(MEMBER_ID_HEADER_NAME, textEncryptor.decrypt(subject));
    }
}
