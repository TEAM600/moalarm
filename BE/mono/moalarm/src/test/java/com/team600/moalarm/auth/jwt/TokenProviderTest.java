package com.team600.moalarm.auth.jwt;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import com.team600.moalarm.auth.vo.JwtDecryptResult;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TokenProviderTest {

    TokenProvider tokenProvider;

    @BeforeEach
    void setUp() {
        tokenProvider = new TokenProvider("a".repeat(256), 30);
    }

    @Test
    void decryptJwt() {
        String subject = "test";
        String token = tokenProvider.createToken(subject);

        JwtDecryptResult jwtDecryptResult = tokenProvider.decryptJwt(token);

        assertThat(jwtDecryptResult.getSubject()).isEqualTo(subject);
    }

}