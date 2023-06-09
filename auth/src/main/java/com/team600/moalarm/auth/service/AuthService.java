package com.team600.moalarm.auth.service;

import com.team600.moalarm.auth.data.repository.MemberRepository;
import com.team600.moalarm.auth.data.dto.request.SignInRequest;
import com.team600.moalarm.auth.data.dto.response.SignInResponse;
import com.team600.moalarm.auth.exception.SignInFailedException;
import com.team600.moalarm.auth.data.entity.Member;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Slf4j
@RequiredArgsConstructor
@Service
public class AuthService {

    private final MemberRepository memberRepository;
    private final EncryptJWTManager encryptJWTManager;
    private final PasswordEncoder passwordEncoder;


    public SignInResponse signIn(SignInRequest signInRequest) {
        Member member = memberRepository.findByEmail(signInRequest.getEmail())
                .orElseThrow(SignInFailedException::new);

        if (!passwordEncoder.matches(signInRequest.getPassword(), member.getPassword())) {
            throw new SignInFailedException();
        }

        return new SignInResponse(encryptJWTManager.createToken(String.valueOf(member.getId())));
    }

}
