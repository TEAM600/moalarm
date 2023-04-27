package com.team600.moalarm.member.service;

import com.team600.moalarm.apikey.service.ApiKeyGenerator;
import com.team600.moalarm.member.dto.request.SignUpRequest;
import com.team600.moalarm.member.entity.Member;
import com.team600.moalarm.member.exception.EmailConflictException;
import com.team600.moalarm.member.repository.MemberRepository;
import java.time.LocalDateTime;
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
    private final ApiKeyGenerator apiKeyGenerator;

    public void signUp(SignUpRequest signUpRequest) {
        if (memberRepository.existsByEmail(signUpRequest.getEmail())) {
            throw new EmailConflictException();
        }

        String encodedPassword = passwordEncoder.encode(signUpRequest.getPassword());
        String apiKey = apiKeyGenerator.createApiKey();

        Member member = Member.builder()
                .email(signUpRequest.getEmail())
                .password(encodedPassword)
                .moalarmKey(apiKey)
                .moalarmKeyRefreshedAt(LocalDateTime.now())
                .build();

        memberRepository.save(member);
    }

}
