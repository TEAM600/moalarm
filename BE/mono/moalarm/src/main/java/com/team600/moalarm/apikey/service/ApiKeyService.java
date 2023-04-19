package com.team600.moalarm.apikey.service;

import com.team600.moalarm.apikey.dto.response.ApiKeyDto;

public interface ApiKeyService {
    ApiKeyDto getApiKey(String memberId);
    ApiKeyDto updateApiKey(String memberId);
}
