package com.team600.moalarm.common.config;

import com.team600.moalarm.auth.filter.JwtAuthFilter;
import java.util.Arrays;
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

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final JwtAuthFilter jwtAuthFilter;

    public SecurityConfig(JwtAuthFilter jwtAuthFilter) {
        this.jwtAuthFilter = jwtAuthFilter;
    }

    @Bean
    public SecurityFilterChain web(HttpSecurity http) throws Exception {
        return http.addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .sessionManagement(httpSecuritySessionManagementConfigurer ->
                        httpSecuritySessionManagementConfigurer.sessionCreationPolicy(
                                SessionCreationPolicy.STATELESS))
                .csrf(AbstractHttpConfigurer::disable)
                .formLogin(AbstractHttpConfigurer::disable)
                .authorizeRequests(authorize -> authorize
                        .antMatchers("/auth/signin").permitAll()
                        .antMatchers(HttpMethod.POST, "/member").permitAll()
                        .anyRequest().authenticated()
                )
                .build();
    }

    @Order(0)
    @Bean
    public SecurityFilterChain cors(HttpSecurity http) throws Exception {
        return http.cors(httpSecurityCorsConfigurer ->
                httpSecurityCorsConfigurer.configurationSource(
                        request -> {
                            CorsConfiguration corsConfiguration = new CorsConfiguration();
                            corsConfiguration.applyPermitDefaultValues();
                            corsConfiguration.setExposedHeaders(
                                    Arrays.asList("Authorization", "Access-Control-Allow-Origin"));
                            return corsConfiguration;
                        })).build();
    }
}
