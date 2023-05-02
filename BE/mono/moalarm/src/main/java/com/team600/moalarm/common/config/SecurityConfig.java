package com.team600.moalarm.common.config;

import com.team600.moalarm.common.config.filter.ExceptionHandlerFilter;
import com.team600.moalarm.common.config.filter.JwtAuthFilter;
import com.team600.moalarm.common.config.filter.MoalarmKeyAuthFilter;
import lombok.RequiredArgsConstructor;
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

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    public static final String[] ENDPOINTS_WHITELIST_POST = {
            "/auth/signin",
            "/member"
    };
    public static final String[] ENDPOINTS_ROLE_API = {
            "/test/**",
            "/notification/**"
    };
    private final MoalarmKeyAuthFilter moalarmKeyFilter;
    private final JwtAuthFilter jwtAuthFilter;
    private final ExceptionHandlerFilter exceptionHandlerFilter;

    @Order(0)
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
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
