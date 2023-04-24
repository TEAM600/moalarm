package com.team600.moalarm.apikey.service.impl;

import com.team600.moalarm.apikey.dto.response.MoalarmKeyDto;
import com.team600.moalarm.apikey.service.ApiKeyGenerator;
import com.team600.moalarm.apikey.service.MoalarmKeyService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MoalarmKeyServiceImpl implements MoalarmKeyService {

    @Autowired
    private final ApiKeyGenerator apiKeyGenerator;

    @Override
    public MoalarmKeyDto getApiKey(String memberId) {
        String newKey = null;   //TODO: get member(api_key)
        return new MoalarmKeyDto(newKey);
    }

    @Override
    public MoalarmKeyDto refreshApiKey(String memberId) {
        String newKey = apiKeyGenerator.createApiKey();
        // TODO: update member(api_key, api_key_refreshed_at)
        return new MoalarmKeyDto(newKey);
    }
}
