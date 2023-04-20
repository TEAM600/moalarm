package com.team600.moalarm.auth.service;

import com.team600.moalarm.auth.dto.request.SignIn;
import com.team600.moalarm.auth.dto.response.Token;
import com.team600.moalarm.auth.jwt.TokenProvider;
import com.team600.moalarm.member.entiry.Member;
import com.team600.moalarm.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Slf4j
@RequiredArgsConstructor
@Service
public class AuthService {

    private final MemberRepository memberRepository;
    private final TokenProvider tokenProvider;

    public Token signIn(SignIn signIn) {
        Member member = memberRepository.findByEmail(signIn.getEmail()).orElseThrow(
                () -> new RuntimeException("user not found"));// TODO: 2023-04-19 Exception 정의 필요

        if (!member.getPassword().equals(signIn.getPassword())) {
            throw new RuntimeException("password not correct"); // TODO: 2023-04-19 Exception 정의 필요
        }

        return tokenProvider.createToken(member.getEmail());
    }

}
