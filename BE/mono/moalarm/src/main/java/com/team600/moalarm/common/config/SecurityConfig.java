package com.team600.moalarm.common.config;

import com.team600.moalarm.auth.filter.JwtAuthFilter;
import com.team600.moalarm.common.config.filter.ExceptionHandlerFilter;
import com.team600.moalarm.common.config.filter.MoalarmKeyFilter;
import lombok.RequiredArgsConstructor;
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

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final JwtAuthFilter jwtAuthFilter;
    private final MoalarmKeyFilter moalarmKeyFilter;
    private final ExceptionHandlerFilter exceptionHandlerFilter;

    @Bean
    public SecurityFilterChain apiFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorize -> authorize
                        .antMatchers("/test/**").hasRole(MoalarmKeyFilter.ROLE_API)
                        .antMatchers("/notification/**").hasRole(MoalarmKeyFilter.ROLE_API)
                )
                .addFilterBefore(moalarmKeyFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    @Order(1)
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
