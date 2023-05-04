package com.team600.moalarm.channel.service.impl;

import com.team600.moalarm.channel.data.code.ChannelCode;
import com.team600.moalarm.channel.data.dto.request.ChannelCreateRequest;
import com.team600.moalarm.channel.data.entity.Channel;
import com.team600.moalarm.channel.data.repository.ChannelRepository;
import com.team600.moalarm.channel.exception.ChannelConflictException;
import com.team600.moalarm.channel.service.ChannelSaveService;
import com.team600.moalarm.common.component.MemberUtil;
import com.team600.moalarm.member.entity.Member;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Slf4j
@Service
public class FcmChannelService implements ChannelSaveService {

    private final ChannelRepository channelRepository;
    private final MemberUtil memberUtil;

    @Override
    @Transactional
    public void saveChannel(ChannelCreateRequest requestDto, long memberId) {
        log.info("FCM Channel Save");
        Member member = memberUtil.getMemberByMemberId(memberId);

        if (channelRepository.existsByMemberIdAndType(member.getId(), ChannelCode.SMS)) {
            throw new ChannelConflictException();
        }

        Channel channel = Channel.builder()
                .memberId(member.getId())
                .type(ChannelCode.FCM)
                .extraValue(requestDto.getExtraValue())
                .build();
        member.registChannel(ChannelCode.FCM.ordinal());
        channelRepository.save(channel);
    }
}
