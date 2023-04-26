package com.team600.moalarm.apikey.service.impl;

import com.team600.moalarm.apikey.dto.response.MoalarmKeyResponse;
import com.team600.moalarm.apikey.service.ApiKeyGenerator;
import com.team600.moalarm.apikey.service.ApiKeyService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MoalarmKeyServiceImpl implements ApiKeyService<MoalarmKeyResponse> {

    @Autowired
    private final ApiKeyGenerator apiKeyGenerator;

    @Override
    public MoalarmKeyResponse getApiKey(String memberId) {
        String newKey = null;   //TODO: get member(api_key)
        return new MoalarmKeyResponse(newKey);
    }

    @Override
    public MoalarmKeyResponse refreshApiKey(String memberId) {
        String newKey = apiKeyGenerator.createApiKey();
        // TODO: update member(api_key, api_key_refreshed_at)
        return new MoalarmKeyResponse(newKey);
    }
}
