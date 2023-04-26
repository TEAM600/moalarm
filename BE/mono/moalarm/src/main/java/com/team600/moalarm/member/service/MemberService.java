package com.team600.moalarm.member.service;

import com.team600.moalarm.member.dto.request.SignUpRequest;
import com.team600.moalarm.member.entity.Member;
import com.team600.moalarm.member.exception.EmailConflictException;
import com.team600.moalarm.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    public void signUp(SignUpRequest signUpRequest) {
        log.info("[SignUp] start");
        if (memberRepository.existsByEmail(signUpRequest.getEmail())) {
            throw new EmailConflictException();
        }

        memberRepository.existsById(1L);

        String encodedPassword = passwordEncoder.encode(signUpRequest.getPassword());
        log.info("[SignUp] encodedPassword = {}", encodedPassword);

        Member member = Member.builder()
                .email(signUpRequest.getEmail())
                .password(encodedPassword)
                .build();

        memberRepository.save(member);
        log.info("[SignUp] end");
    }

}
