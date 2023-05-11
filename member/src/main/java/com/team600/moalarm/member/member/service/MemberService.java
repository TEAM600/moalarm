package com.team600.moalarm.member.member.service;

import com.team600.moalarm.member.apikey.service.ApiKeyGenerator;
import com.team600.moalarm.member.channel.data.code.ChannelCode;
import com.team600.moalarm.member.channel.data.dto.response.ChannelRegistrationResponse;
import com.team600.moalarm.member.member.entity.Member;
import com.team600.moalarm.member.member.exception.EmailConflictException;
import com.team600.moalarm.member.member.exception.MemberNotFoundException;
import com.team600.moalarm.member.member.repository.MemberRepository;
import com.team600.moalarm.member.member.request.SignUpRequest;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Service
public class MemberService<ID> {

    private final MemberRepository<ID> memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final ApiKeyGenerator apiKeyGenerator;

    @Transactional
    public void signUp(SignUpRequest signUpRequest) {
        if (memberRepository.existsByEmail(signUpRequest.getEmail())) {
            throw new EmailConflictException();
        }

        String encodedPassword = passwordEncoder.encode(signUpRequest.getPassword());
        String apiKey = apiKeyGenerator.generateKey();

        Member member = Member.builder()
                .email(signUpRequest.getEmail())
                .password(encodedPassword)
                .moalarmKey(apiKey)
                .moalarmKeyRefreshedAt(LocalDateTime.now())
                .build();

        memberRepository.save(member);
    }

    @Transactional
    public void withdrawal(ID memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(MemberNotFoundException::new);

        member.remove();
    }

    @Transactional(readOnly = true)
    public List<ChannelRegistrationResponse> getChannels(ID memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(MemberNotFoundException::new);
        List<ChannelRegistrationResponse> returnDto = new ArrayList<>();
        int channelRegistrationStatus = member.getChannelRegistrationStatus();

        for (ChannelCode code : ChannelCode.values()) {
            returnDto.add(ChannelRegistrationResponse.builder()
                    .type(code.getValue())
                    .registration(checkRegistration(channelRegistrationStatus, code.ordinal()))
                    .build());
        }
        return returnDto;
    }

    private boolean checkRegistration(int channelRegistrationStatus, int codeOrdinal) {
        return (channelRegistrationStatus & (1 << codeOrdinal)) != 0;
    }
}
