package com.team600.moalarm.gateway.common.config;

import static org.springframework.security.config.Customizer.withDefaults;

import java.util.Arrays;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity.CsrfSpec;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.web.cors.CorsConfiguration;

@RequiredArgsConstructor
@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {

    @Value("${security.allowed-origins}")
    private final List<String> allowedOrigins;
    private final String[] ENDPOINTS_WHITELIST_WITH_POST_METHOD = {
            "/auth/signin",
            "/member"
    };
    private final String[] ENDPOINTS_MOALARM_API = {
            "/notification/**"
    };

    @Bean
    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
        http
                .cors(httpSecurityCorsConfigurer -> httpSecurityCorsConfigurer
                        .configurationSource(
                                request -> {
                                    CorsConfiguration configuration = new CorsConfiguration();
                                    configuration.applyPermitDefaultValues();

                                    configuration.setAllowedOrigins(allowedOrigins);
                                    configuration.setAllowedMethods(
                                            Arrays.asList("GET", "POST", "OPTIONS", "PUT", "PATCH",
                                                    "DELETE"));
                                    return configuration;
                                }))
                .authorizeExchange(exchanges -> exchanges
                        .anyExchange().permitAll())
                .httpBasic(withDefaults())
                .csrf(CsrfSpec::disable);

        return http.build();
    }
}
