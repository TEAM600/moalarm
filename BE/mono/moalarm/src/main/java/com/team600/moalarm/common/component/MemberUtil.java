package com.team600.moalarm.common.component;

import com.team600.moalarm.member.entity.Member;
import com.team600.moalarm.member.exception.MemberNotFoundException;
import com.team600.moalarm.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class MemberUtil {

    private final MemberRepository memberRepository;

    public Member getMemberMoalarmKey(String moalarmKey) {
        return memberRepository.findByMoalarmKey(moalarmKey)
                .orElseThrow(() -> new MemberNotFoundException("존재하지 않는 유저입니다."));
    }

    public Member getMemberByMemberId(String memberId) {
        return memberRepository.findById(Long.parseLong(memberId))
                .orElseThrow(() -> new MemberNotFoundException("존재하지 않는 유저입니다."));
    }
}