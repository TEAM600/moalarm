package com.team600.moalarm.gateway.common.config.filter;

import com.team600.moalarm.gateway.common.config.provider.TokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import com.team600.moalarm.gateway.common.config.vo.JwtDecryptResult;

@Component
public class JwtDecodeFilter extends AbstractGatewayFilterFactory<JwtDecodeFilter.Config> {
    private final TokenProvider tokenProvider;
    private static final String BEARER = "Bearer ";

    @Autowired
    public JwtDecodeFilter(TokenProvider tokenProvider) {
        super(Config.class);
        this.tokenProvider = tokenProvider;
        System.out.println("만들어짐?");
    }

    public static class Config {
        //Put the configuration properties
    }

    @Override
    public GatewayFilter apply(Config config) {
        //Custom Pre Filter
        return (exchange, chain) -> {
            String token = getToken(exchange);
            if (!tokenProvider.validateAccessToken(token)) {
                throw new RuntimeException("토큰 유효성 검사 실패");
            }
            JwtDecryptResult jwtDecryptResult = tokenProvider.decryptJwt(token);
            addAuthorizationHeaders(exchange.getRequest(), jwtDecryptResult.getSubject());

            System.out.println(exchange.getRequest().getHeaders());
            return chain.filter(exchange);
        };
    }

    private String getToken(ServerWebExchange exchange) {
        return exchange.getRequest().getHeaders().get("Authorization").get(0)
                .substring(BEARER.length());   // 헤더의 토큰 파싱 (Bearer 제거)
    }

    private void addAuthorizationHeaders(ServerHttpRequest request, String subject) {
        request.getHeaders().add("Member-Id", subject);
    }
}
