package com.team600.moalarm.auth.service;

import com.team600.moalarm.auth.jwt.TokenProvider;
import com.team600.moalarm.auth.vo.JwtDecryptResult;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.encrypt.TextEncryptor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class EncryptJWTManager {

    private final TokenProvider tokenProvider;
    private final TextEncryptor encryptor;

    public String createToken(String subject) {
        String encryptedSubject = encryptor.encrypt(subject);
        return tokenProvider.createToken(encryptedSubject);
    }

    public JwtDecryptResult decryptJwt(String token) {
        JwtDecryptResult decryptedResult = null;

        if (token != null && tokenProvider.validateAccessToken(token)) {
            JwtDecryptResult jwtDecryptResult = tokenProvider.decryptJwt(token);

            try {
                String decryptedSubject = encryptor.decrypt(jwtDecryptResult.getSubject());
                decryptedResult = new JwtDecryptResult(decryptedSubject,
                        jwtDecryptResult.getAuthorities());
            } catch (Exception e) {
                //TODO
            }

        }

        return decryptedResult;
    }
}
