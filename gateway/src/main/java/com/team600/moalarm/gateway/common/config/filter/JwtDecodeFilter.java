package com.team600.moalarm.gateway.common.config.filter;

import com.team600.moalarm.gateway.common.config.filter.JwtDecodeFilter.Config;
import com.team600.moalarm.gateway.common.config.provider.TokenProvider;
import com.team600.moalarm.gateway.common.config.vo.JwtDecryptResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.GatewayFilterFactory;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.security.crypto.encrypt.TextEncryptor;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

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
            ServerHttpRequest request = exchange.getRequest();
            String domain = request.getPath().subPath(5).toString();

            if (domain.equals("member") && request.getMethod() == HttpMethod.POST) {
                request.mutate().header(MEMBER_ID_HEADER_NAME, "0");
                return chain.filter(exchange);
            }
            String token = getToken(exchange);
            try {
                tokenProvider.validateAccessToken(token);
                JwtDecryptResult jwtDecryptResult = tokenProvider.parseJwt(token);
                addAuthorizationHeaders(request, jwtDecryptResult.getSubject());
            } catch (Exception e) {
                return chain.filter(exchange).onErrorResume(throwable -> {
                    ServerHttpResponse response = exchange.getResponse();
                    response.getHeaders().setContentType(MediaType.APPLICATION_JSON);
                    DataBuffer dataBuffer = exchange.getResponse().bufferFactory()
                            .wrap(throwable.getMessage().getBytes());
                    response.setStatusCode(HttpStatus.UNAUTHORIZED);

                    return response.writeWith(Mono.just(dataBuffer)).log();
                });
            }

            return chain.filter(exchange).onErrorResume(
                    throwable -> {
                        ServerHttpResponse response = exchange.getResponse();
                        response.getHeaders().setContentType(MediaType.APPLICATION_JSON);
                        DataBuffer dataBuffer = exchange.getResponse().bufferFactory()
                                .wrap(throwable.getMessage().substring(
                                                "Connection refused: no further information: ".length())
                                        .getBytes());
                        response.setStatusCode(HttpStatus.UNAUTHORIZED);

                        return response.writeWith(Mono.just(dataBuffer)).log();
                    }
            ).log();
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
