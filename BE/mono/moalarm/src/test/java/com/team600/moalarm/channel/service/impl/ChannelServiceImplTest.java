package com.team600.moalarm.channel.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import com.team600.moalarm.alarm.service.SenderService;
import com.team600.moalarm.channel.data.code.ChannelCode;
import com.team600.moalarm.channel.data.entity.Channel;
import com.team600.moalarm.channel.data.repository.ChannelRepository;
import com.team600.moalarm.common.component.MemberUtil;
import com.team600.moalarm.member.entity.Member;
import com.team600.moalarm.member.repository.MemberRepository;
import java.util.Map;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ChannelServiceImplTest {

    @Mock
    ChannelRepository channelRepository;
    @Mock
    Map<String, SenderService> senderService;
    @Mock
    MemberRepository memberRepository;
    ChannelServiceImpl channelServiceImpl;
    MemberUtil memberUtil;

    @BeforeEach
    void setUp() {
        this.memberUtil = new MemberUtil(memberRepository);
        this.channelServiceImpl = new ChannelServiceImpl(channelRepository, memberUtil,
                senderService);
    }

    @Test
    @DisplayName("체널이 삭제가 되었는가")
    void deleteChannelSuccessTest() {
        ChannelCode channelCode = ChannelCode.SMS;
        long memberId = 1L;
        String apiKey = "1";
        String secret = "1";

        String email = "w@a.com";
        String password = "1234";
        String moalarmKey = "1234";

        Channel channel = getChannel(memberId, apiKey, secret, channelCode);
        Member member = getMember(email, password, channelCode, moalarmKey);

        member.setId(memberId);
        channel.setDelYn("N");

        when(channelRepository.findByTypeAndMemberId(channelCode, memberId))
                .thenReturn(Optional.of(channel));
        when(memberRepository.findById(1L))
                .thenReturn(Optional.of(member));

        channelServiceImpl.deleteChannel(channelCode, memberId);

        assertEquals(channel.getDelYn(), "Y"); // 삭제가 되면 channel의 delyn필드가 문자열 Y가 되어야함
        assertEquals(member.getChannelRegistrationStatus(),
                0); // 삭제가 되면 registration bitmask int값이 0이 되어야함
    }

    private Member getMember(String email, String password, ChannelCode channelCode,
            String moaramKey) {
        return Member.builder()
                .email(email)
                .password(password)
                .moalarmKey(moaramKey)
                .channelRegistrationStatus(1 << channelCode.ordinal())
                .build();
    }

    private Channel getChannel(long memberId, String apiKey, String secret,
            ChannelCode channelCode) {
        return Channel.builder()
                .memberId(memberId)
                .apiKey(apiKey)
                .secret(secret)
                .type(channelCode)
                .build();
    }
}