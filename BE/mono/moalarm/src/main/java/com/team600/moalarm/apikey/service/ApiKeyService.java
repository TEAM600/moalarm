package com.team600.moalarm.apikey.service;

import com.team600.moalarm.apikey.dto.response.KeyResponse;

public interface ApiKeyService<T extends KeyResponse> {

    T getApiKey(long memberId);

    T refreshApiKey(long memberId);
}
