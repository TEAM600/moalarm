package com.team600.moalarm.member.common.utils;

import com.team600.moalarm.member.member.entity.Member;
import com.team600.moalarm.member.member.exception.MemberNotFoundException;
import com.team600.moalarm.member.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class MemberUtil<ID> {

    private final MemberRepository<ID> memberRepository;

    public Member getMemberMoalarmKey(String moalarmKey) {
        return memberRepository.findByMoalarmKey(moalarmKey)
                .orElseThrow(MemberNotFoundException::new);
    }

    public Member getMemberByMemberId(ID memberId) {
        return memberRepository.findById(memberId)
                .orElseThrow(MemberNotFoundException::new);
    }
}
