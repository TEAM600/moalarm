package com.team600.moalarm.auth.service;

import com.team600.moalarm.auth.jwt.TokenProvider;
import com.team600.moalarm.auth.vo.JwtAuthResult;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.encrypt.TextEncryptor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class TokenManager {

    private final TokenProvider tokenProvider;
    private final TextEncryptor encryptor;

    public String createToken(String subject) {
        String encryptedSubject = encryptor.encrypt(subject);
        return tokenProvider.createToken(encryptedSubject);
    }

    public Authentication getAuthentication(String token) {
        Authentication authentication = null;

        if (token != null && tokenProvider.validateAccessToken(token)) {
            JwtAuthResult jwtAuthResult = tokenProvider.getAuthentication(token);
            String decryptedSubject = encryptor.decrypt(jwtAuthResult.getSubject());
            User principal = new User(decryptedSubject, "", jwtAuthResult.getAuthorities());
            authentication = new UsernamePasswordAuthenticationToken(principal, token,
                    jwtAuthResult.getAuthorities());
        }

        return authentication;
    }
}
