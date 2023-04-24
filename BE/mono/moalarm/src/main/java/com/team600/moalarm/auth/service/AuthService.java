package com.team600.moalarm.auth.service;

import com.team600.moalarm.auth.dto.request.SignInRequest;
import com.team600.moalarm.auth.dto.response.SignInResponse;
import com.team600.moalarm.auth.jwt.TokenProvider;
import com.team600.moalarm.member.entity.Member;
import com.team600.moalarm.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.encrypt.TextEncryptor;
import org.springframework.stereotype.Service;


@Slf4j
@RequiredArgsConstructor
@Service
public class AuthService {

    private final MemberRepository memberRepository;
    private final TokenProvider tokenProvider;
    private final TextEncryptor encryptor;

    public SignInResponse signIn(SignInRequest signInRequest) {
        Member member = memberRepository.findByEmail(signInRequest.getEmail()).orElseThrow(
                () -> new RuntimeException("user not found"));// TODO: 2023-04-19 Exception 정의 필요

        if (!member.getPassword().equals(signInRequest.getPassword())) {
            throw new RuntimeException("password not correct"); // TODO: 2023-04-19 Exception 정의 필요
        }

        return new SignInResponse(tokenProvider.createToken(
                encryptor.encrypt(member.getEmail())));
    }

}
