package com.team600.moalarm.apikey.service.impl;

import com.team600.moalarm.apikey.service.ApiKeyGenerator;
import java.security.SecureRandom;
import java.util.Base64;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class RandomStringApiKeyGenerator implements ApiKeyGenerator {

    public String createApiKey() {
        SecureRandom random = new SecureRandom();
        byte[] bytes = new byte[32];
        random.nextBytes(bytes);
        return Base64.getEncoder().encodeToString(bytes);
    }
}
