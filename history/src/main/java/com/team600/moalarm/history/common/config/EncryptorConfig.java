package com.team600.moalarm.history.common.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.security.crypto.encrypt.TextEncryptor;

@Configuration
public class EncryptorConfig {

    private final String secret;
    public final String salt;

    public EncryptorConfig(@Value("${crypto.secret}") String secret,
            @Value("${crypto.salt}") String salt) {
        this.secret = secret;
        this.salt = salt;
    }

    @Bean
    public TextEncryptor encryptor() {
        return Encryptors.text(secret, salt);
    }
}
