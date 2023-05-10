package com.team600.moalarm.member.apikey.service;

import com.team600.moalarm.member.apikey.response.KeyResponse;

public interface ApiKeyService<ID, T extends KeyResponse> {

    T getApiKey(ID memberId);

    T refreshApiKey(ID memberId);
}
