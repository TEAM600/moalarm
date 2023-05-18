package com.team600.moalarm.gateway.common.config.filter;

import com.team600.moalarm.gateway.common.config.filter.JwtDecodeFilter.Config;
import com.team600.moalarm.gateway.common.config.provider.TokenProvider;
import com.team600.moalarm.gateway.common.config.vo.JwtDecryptResult;
import com.team600.moalarm.gateway.common.exception.impl.TokenNotFoundException;
import com.team600.moalarm.gateway.common.exception.impl.TokenValidateException;
import io.jsonwebtoken.MalformedJwtException;
import java.net.ConnectException;
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
            return getToken(request).flatMap(token -> {
                        try {
                            tokenProvider.validateAccessToken(token);
                            JwtDecryptResult jwtDecryptResult = tokenProvider.parseJwt(token);
                            addAuthorizationHeaders(request, jwtDecryptResult.getSubject());
                            return chain.filter(exchange);
                        } catch (TokenValidateException e) {
                            return Mono.error(e);
                        }
                    }).onErrorResume(ConnectException.class, e -> {
                        ServerHttpResponse response = exchange.getResponse();
                        response.getHeaders().setContentType(MediaType.APPLICATION_JSON);
                        DataBuffer errorDataBuffer = exchange.getResponse().bufferFactory()
                                .wrap(e.getMessage().substring(
                                                "Connection refused: no further information: ".length())
                                        .getBytes());
                        response.setStatusCode(HttpStatus.SERVICE_UNAVAILABLE);
                        return response.writeWith(Mono.just(errorDataBuffer));
                    }).onErrorResume(TokenNotFoundException.class, e -> {
                        ServerHttpResponse response = exchange.getResponse();
                        response.getHeaders().setContentType(MediaType.APPLICATION_JSON);
                        DataBuffer errorDataBuffer = exchange.getResponse().bufferFactory()
                                .wrap(e.getMessage().getBytes());
                        response.setStatusCode(HttpStatus.UNAUTHORIZED);
                        return response.writeWith(Mono.just(errorDataBuffer));
                    }).onErrorResume(TokenValidateException.class, e -> {
                        ServerHttpResponse response = exchange.getResponse();
                        response.getHeaders().setContentType(MediaType.APPLICATION_JSON);
                        DataBuffer errorDataBuffer = exchange.getResponse().bufferFactory()
                                .wrap(e.getMessage().getBytes());
                        response.setStatusCode(HttpStatus.UNAUTHORIZED);
                        return response.writeWith(Mono.just(errorDataBuffer));
                    }).onErrorResume(MalformedJwtException.class, e -> {
                        ServerHttpResponse response = exchange.getResponse();
                        response.getHeaders().setContentType(MediaType.APPLICATION_JSON);
                        DataBuffer errorDataBuffer = exchange.getResponse().bufferFactory()
                                .wrap(e.getMessage().getBytes());
                        response.setStatusCode(HttpStatus.UNAUTHORIZED);
                        return response.writeWith(Mono.just(errorDataBuffer));
                    })
                    .log();
        };
    }

    @Override
    public Class<Config> getConfigClass() {
        return Config.class;
    }

    private Mono<String> getToken(ServerHttpRequest request) {
        String token = request.getHeaders().getFirst("Authorization");

        if (token == null || token.equals("")) {
            return Mono.error(TokenNotFoundException::new);
        }
        return Mono.just(token.substring(BEARER.length()));
    }


    private void addAuthorizationHeaders(ServerHttpRequest request, String subject) {
        request.mutate().header(MEMBER_ID_HEADER_NAME, textEncryptor.decrypt(subject));
    }
}
