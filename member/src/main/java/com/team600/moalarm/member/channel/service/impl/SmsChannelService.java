package com.team600.moalarm.member.channel.service.impl;

import com.team600.moalarm.member.channel.data.code.ChannelCode;
import com.team600.moalarm.member.channel.data.dto.request.ChannelCreateRequest;
import com.team600.moalarm.member.channel.data.entity.Channel;
import com.team600.moalarm.member.channel.data.repository.ChannelRepository;
import com.team600.moalarm.member.channel.exception.ChannelConflictException;
import com.team600.moalarm.member.channel.service.ChannelSaveService;
import com.team600.moalarm.member.common.component.MemberUtil;
import com.team600.moalarm.member.member.entity.Member;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Slf4j
@Service
public class SmsChannelService implements ChannelSaveService {

    private final ChannelRepository channelRepository;
    private final MemberUtil memberUtil;

    @Override
    @Transactional
    public void saveChannel(ChannelCreateRequest requestDto, long memberId) {
        log.info("SMS Channel Save");
        Member member = memberUtil.getMemberByMemberId(memberId);

        if (channelRepository.existsByMemberIdAndType(member.getId(), ChannelCode.SMS)) {
            throw new ChannelConflictException();
        }

        Channel channel = Channel.builder()
                .apiKey(requestDto.getKey())
                .memberId(member.getId())
                .type(ChannelCode.SMS)
                .secret(requestDto.getSecret())
                .extraValue(requestDto.getExtraValue())
                .build();
        member.registChannel(ChannelCode.SMS.ordinal());
        channelRepository.save(channel);
    }
}
