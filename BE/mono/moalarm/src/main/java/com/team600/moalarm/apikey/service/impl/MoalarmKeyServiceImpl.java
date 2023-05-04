package com.team600.moalarm.apikey.service.impl;

import com.team600.moalarm.apikey.dto.response.MoalarmKeyResponse;
import com.team600.moalarm.apikey.service.ApiKeyGenerator;
import com.team600.moalarm.apikey.service.ApiKeyService;
import com.team600.moalarm.member.data.entity.Member;
import com.team600.moalarm.member.data.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Service
public class MoalarmKeyServiceImpl implements ApiKeyService<MoalarmKeyResponse> {

    private final MemberRepository memberRepository;
    private final ApiKeyGenerator apiKeyGenerator;

    @Transactional(readOnly = true)
    @Override
    public MoalarmKeyResponse getApiKey(long memberId) {
        Member member = memberRepository.findById(memberId).orElseThrow();
        String apiKey = member.getMoalarmKey();
        return new MoalarmKeyResponse(apiKey);
    }

    @Transactional
    @Override
    public MoalarmKeyResponse refreshApiKey(long memberId) {
        Member member = memberRepository.findById(memberId).orElseThrow();
        String newKey = apiKeyGenerator.createApiKey();
        member.refreshMoalarmKey(newKey);
        return new MoalarmKeyResponse(newKey);
    }
}
