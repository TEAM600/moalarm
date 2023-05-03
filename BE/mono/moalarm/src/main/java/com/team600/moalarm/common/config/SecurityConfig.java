package com.team600.moalarm.common.config;

import com.team600.moalarm.auth.filter.JwtAuthFilter;
import com.team600.moalarm.common.config.filter.ExceptionHandlerFilter;
import com.team600.moalarm.common.config.filter.MoalarmKeyFilter;
import java.util.Arrays;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
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

    private final JwtAuthFilter jwtAuthFilter;
    private final MoalarmKeyFilter moalarmKeyFilter;
    private final ExceptionHandlerFilter exceptionHandlerFilter;
    @Value("${security.allowed-origins}")
    private final List<String> allowedOrigins;

    @Bean
    public SecurityFilterChain apiFilterChain(HttpSecurity http) throws Exception {
        return http.authorizeHttpRequests(authorize -> authorize
                        .antMatchers("/test/**").hasRole(MoalarmKeyFilter.ROLE_API)
                        .antMatchers("/notification/**").hasRole(MoalarmKeyFilter.ROLE_API)
                )
                .addFilterBefore(moalarmKeyFilter,
                        UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Order(0)
    @Bean
    public SecurityFilterChain webFilterChain(HttpSecurity http) throws Exception {
        return http
                .addFilterBefore(exceptionHandlerFilter, UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .sessionManagement(httpSecuritySessionManagementConfigurer ->
                        httpSecuritySessionManagementConfigurer.sessionCreationPolicy(
                                SessionCreationPolicy.STATELESS))
                .csrf(AbstractHttpConfigurer::disable)
                .formLogin(AbstractHttpConfigurer::disable)
                .authorizeRequests(authorize -> authorize
                        .antMatchers("/auth/signin").permitAll()
                        .antMatchers(HttpMethod.POST, "/member").permitAll()
                        .antMatchers("/member/**", "/key/**", "/channels/**").authenticated()
                )
                .cors(httpSecurityCorsConfigurer ->
                        httpSecurityCorsConfigurer.configurationSource(
                                request -> {
                                    CorsConfiguration configuration = new CorsConfiguration();
                                    configuration.applyPermitDefaultValues();

                                    configuration.setAllowedOrigins(allowedOrigins);
                                    configuration.setAllowedMethods(
                                            Arrays.asList("GET", "POST", "OPTIONS", "PUT", "PATCH",
                                                    "DELETE"));
                                    return configuration;
                                }))
                .build();
    }

}
