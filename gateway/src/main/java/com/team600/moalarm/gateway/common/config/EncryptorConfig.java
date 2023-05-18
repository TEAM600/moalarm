package com.team600.moalarm.gateway.common.config;

import com.team600.moalarm.gateway.common.config.filter.JwtDecodeFilter;
import com.team600.moalarm.gateway.common.config.provider.TokenProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
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

    @Bean(name = "JwtSubjectEncryptor")
    public TextEncryptor encryptor() {
        TextEncryptor textEncryptor = Encryptors.text(secret, salt);
        return textEncryptor;
    }
}
