package com.team600.moalarm.member.apikey.service.impl;

import com.team600.moalarm.member.apikey.response.MoalarmKeyResponse;
import com.team600.moalarm.member.apikey.service.ApiKeyGenerator;
import com.team600.moalarm.member.apikey.service.ApiKeyService;
import com.team600.moalarm.member.member.entity.Member;
import com.team600.moalarm.member.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Service
public class MoalarmKeyServiceImpl<ID> implements ApiKeyService<ID, MoalarmKeyResponse> {

    private final MemberRepository<ID> memberRepository;
    private final ApiKeyGenerator apiKeyGenerator;

    @Transactional(readOnly = true)
    @Override
    public MoalarmKeyResponse getApiKey(ID memberId) {
        Member member = memberRepository.findById(memberId).orElseThrow();
        String apiKey = member.getMoalarmKey();
        return new MoalarmKeyResponse(apiKey);
    }

    @Transactional
    @Override
    public MoalarmKeyResponse refreshApiKey(ID memberId) {
        Member member = memberRepository.findById(memberId).orElseThrow();
        String newKey = apiKeyGenerator.generateKey();
        member.refreshMoalarmKey(newKey);
        return new MoalarmKeyResponse(newKey);
    }
}
