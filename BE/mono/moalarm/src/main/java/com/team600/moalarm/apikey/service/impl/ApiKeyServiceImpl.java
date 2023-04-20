package com.team600.moalarm.apikey.service.impl;

import com.team600.moalarm.apikey.dto.response.ApiKeyDto;
import com.team600.moalarm.apikey.service.ApiKeyGenerator;
import com.team600.moalarm.apikey.service.ApiKeyService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ApiKeyServiceImpl implements ApiKeyService {

    @Autowired
    private final ApiKeyGenerator apiKeyGenerator;

    @Override
    public ApiKeyDto getApiKey(String memberId) {
        String newKey = null;   //TODO: get from Member
        return new ApiKeyDto(newKey);
    }

    @Override
    public ApiKeyDto updateApiKey(String memberId) {
        String newKey = apiKeyGenerator.createApiKey();
        // TODO: update member
        return new ApiKeyDto(newKey);
    }
}
