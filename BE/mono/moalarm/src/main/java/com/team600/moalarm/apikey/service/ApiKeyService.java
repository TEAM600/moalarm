package com.team600.moalarm.apikey.service;

import com.team600.moalarm.apikey.dto.response.KeyDto;

public interface ApiKeyService<T extends KeyDto> {

    T getApiKey(String memberId);

    T refreshApiKey(String memberId);
}
