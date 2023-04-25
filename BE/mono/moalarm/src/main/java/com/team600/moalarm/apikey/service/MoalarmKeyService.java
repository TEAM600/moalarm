package com.team600.moalarm.apikey.service;

import com.team600.moalarm.apikey.dto.response.MoalarmKeyResponse;

public interface MoalarmKeyService {

    MoalarmKeyResponse getApiKey(String memberId);

    MoalarmKeyResponse refreshApiKey(String memberId);
}
