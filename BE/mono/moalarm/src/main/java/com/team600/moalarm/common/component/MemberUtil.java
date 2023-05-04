package com.team600.moalarm.common.component;

import com.team600.moalarm.member.data.entity.Member;
import com.team600.moalarm.member.data.repository.MemberRepository;
import com.team600.moalarm.member.exception.MemberNotFoundException;
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
                .orElseThrow(MemberNotFoundException::new);
    }

    public Member getMemberByMemberId(long memberId) {
        return memberRepository.findById(memberId)
                .orElseThrow(MemberNotFoundException::new);
    }
}
