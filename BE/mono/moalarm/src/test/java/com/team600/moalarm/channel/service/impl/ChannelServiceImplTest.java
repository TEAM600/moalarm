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
        Channel channel = getTestChannel(1, "1", "1", channelCode);
        Member member = getTestMember("w@a.com", "1234", channelCode, "1234");

        when(channelRepository.findByMemberIdAndType(channelCode, 0L))
                .thenReturn(channel);

        when(memberRepository.findById(0L))
                .thenReturn(Optional.of(member));
        
        System.out.println(channel.getDelYn());
        channelServiceImpl.deleteChannel(channelCode, 0L);
        assertEquals(channelRepository.findByMemberIdAndType(channelCode, 0L).getDelYn(), "Y");
    }

    private Member getTestMember(String email, String password, ChannelCode channelCode,
            String moaramKey) {
        return Member.builder()
                .email(email)
                .password(password)
                .moalarmKey(moaramKey)
                .channelRegistrationStatus(1 << channelCode.ordinal())
                .build();

    }

    private Channel getTestChannel(long memberId, String apiKey, String secret,
            ChannelCode channelCode) {
        return Channel.builder()
                .memberId(memberId)
                .apiKey(apiKey)
                .secret(secret)
                .type(channelCode)
                .build();
    }
}