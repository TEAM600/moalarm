package com.team600.moalarm.apikey.service;

import com.team600.moalarm.apikey.dto.response.MoalarmKeyDto;

public interface MoalarmKeyService {

    MoalarmKeyDto getApiKey(String memberId);

    MoalarmKeyDto refreshApiKey(String memberId);
}
