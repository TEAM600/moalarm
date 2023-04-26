package com.team600.moalarm.common.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.security.crypto.encrypt.TextEncryptor;
import org.springframework.security.crypto.keygen.KeyGenerators;

@Configuration
public class EncryptorConfig {

    private final String secret;

    public EncryptorConfig(@Value("${crypto.secret}") String secret) {
        this.secret = secret;
    }

    @Bean
    public TextEncryptor encryptor() {
        return Encryptors.text(secret,
                KeyGenerators.string().generateKey());
    }
}
