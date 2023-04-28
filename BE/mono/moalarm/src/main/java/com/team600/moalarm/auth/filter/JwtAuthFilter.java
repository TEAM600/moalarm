package com.team600.moalarm.auth.filter;

import com.team600.moalarm.auth.service.EncryptJWTManager;
import com.team600.moalarm.auth.vo.CustomUserDetails;
import com.team600.moalarm.auth.vo.JwtDecryptResult;
import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@RequiredArgsConstructor
@Slf4j
@Component
public class JwtAuthFilter extends OncePerRequestFilter {

    private static final String AUTHORIZATION_HEADER = "Authorization";
    private static final String AUTHORIZATION_TYPE = "Bearer ";
    private static final String AUTHENTICATION_REQUEST_URL = "/auth";
    private final EncryptJWTManager encryptJWTManager;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
            FilterChain filterChain) throws ServletException, IOException {
        String token = resolveToken(request);

        JwtDecryptResult decryptedJwt = encryptJWTManager.decryptJwt(token);

        if (decryptedJwt != null) {
            CustomUserDetails principal = new CustomUserDetails(decryptedJwt.getSubject(),
                    decryptedJwt.getAuthorities());
            Authentication authentication = new UsernamePasswordAuthenticationToken(principal,
                    token,
                    decryptedJwt.getAuthorities());

            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        filterChain.doFilter(request, response);
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        return request.getRequestURI().contains(AUTHENTICATION_REQUEST_URL);
    }

    private String resolveToken(HttpServletRequest request) {
        String authorization = request.getHeader(AUTHORIZATION_HEADER);
        String token = null;
        if (authorization != null && authorization.startsWith(AUTHORIZATION_TYPE)) {
            token = authorization.replaceFirst("^" + AUTHORIZATION_TYPE, "");
        }
        return token;
    }

}
