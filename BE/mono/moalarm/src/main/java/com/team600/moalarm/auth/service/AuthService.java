package com.team600.moalarm.auth.service;

import com.team600.moalarm.auth.dto.request.SignInRequest;
import com.team600.moalarm.auth.dto.response.SignInResponse;
import com.team600.moalarm.auth.exception.SignInFailedException;
import com.team600.moalarm.member.entity.Member;
import com.team600.moalarm.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Slf4j
@RequiredArgsConstructor
@Service
public class AuthService {

    private final MemberRepository memberRepository;
    private final TokenManager tokenManager;

    public SignInResponse signIn(SignInRequest signInRequest) {
        Member member = memberRepository.findByEmail(signInRequest.getEmail())
                .orElseThrow(SignInFailedException::new);

        if (!member.getPassword().equals(signInRequest.getPassword())) {
            throw new SignInFailedException();
        }

        return new SignInResponse(tokenManager.createToken(member.getEmail()));
    }

}
