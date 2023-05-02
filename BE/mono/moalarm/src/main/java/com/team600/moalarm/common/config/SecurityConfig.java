package com.team600.moalarm.common.config;

import com.team600.moalarm.common.config.filter.ExceptionHandlerFilter;
import com.team600.moalarm.common.config.filter.JwtAuthFilter;
import com.team600.moalarm.common.config.filter.MoalarmKeyAuthFilter;
import java.util.Arrays;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;

@Slf4j
@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Value("${security.allowed-origins}")
    private final List<String> allowedOrigins;
    private final String[] ENDPOINTS_WHITELIST_POST = {
            "/auth/signin",
            "/member"
    };
    private final String[] ENDPOINTS_ROLE_API = {
            "/notification/**"
    };
    private final MoalarmKeyAuthFilter moalarmKeyFilter;
    private final JwtAuthFilter jwtAuthFilter;
    private final ExceptionHandlerFilter exceptionHandlerFilter;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .cors(httpSecurityCorsConfigurer -> httpSecurityCorsConfigurer
                        .configurationSource(
                                request -> {
                                    CorsConfiguration configuration = new CorsConfiguration();
                                    configuration.applyPermitDefaultValues();

                                    configuration.setAllowedOrigins(allowedOrigins);
                                    configuration.setAllowedMethods(
                                            Arrays.asList("GET", "POST", "OPTION", "PUT", "PATCH",
                                                    "DELETE"));
                                    configuration.setExposedHeaders(
                                            Arrays.asList("Authorization",
                                                    "Access-Control-Allow-Origin"));
                                    return configuration;
                                }))
                .sessionManagement(sessionManagement -> sessionManagement
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .csrf(AbstractHttpConfigurer::disable)
                .formLogin(AbstractHttpConfigurer::disable)
                .authorizeRequests(request -> request
                        .antMatchers(HttpMethod.POST, ENDPOINTS_WHITELIST_POST).permitAll()
                        .antMatchers(HttpMethod.GET, ENDPOINTS_ROLE_API)
                        .hasAuthority(MoalarmKeyAuthFilter.ROLE_API)
                        .anyRequest().hasAuthority("USER")
                )
                .addFilterBefore(exceptionHandlerFilter,
                        UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(moalarmKeyFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
