package com.team600.moalarm.member.service;

import com.team600.moalarm.common.service.ApiKeyGenerator;
import com.team600.moalarm.member.dto.response.ApiKeyDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MemberService {
    @Autowired
    private final ApiKeyGenerator apiKeyGenerator;

    public ApiKeyDto updateApiKey() {
        String newKey = apiKeyGenerator.createApiKey();
        // TODO: update member
        return new ApiKeyDto(newKey);
    }
}
