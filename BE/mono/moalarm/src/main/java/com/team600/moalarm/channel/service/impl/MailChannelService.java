package com.team600.moalarm.channel.service.impl;

import com.team600.moalarm.channel.data.code.ChannelCode;
import com.team600.moalarm.channel.data.dto.request.ChannelCreateRequest;
import com.team600.moalarm.channel.data.entity.Channel;
import com.team600.moalarm.channel.data.repository.ChannelRepository;
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
public class MailChannelService implements ChannelSaveService {

    private final ChannelRepository channelRepository;
    private final MemberUtil memberUtil;

    @Override
    @Transactional
    public void saveChannel(ChannelCreateRequest requestDto, String memberId) {
        log.info("Mail Channel Save");
        Member member = memberUtil.getMemberByMemberId(memberId);
        Channel channel = Channel.builder()
                .memberId(member.getId())
                .apiKey(requestDto.getKey())
                .secret(requestDto.getSecret())
                .type(ChannelCode.MAIL)
                .build();
        channelRepository.save(channel);
    }

    @Override
    public void deleteChannel(String type, String memberId) {
        Member member = memberUtil.getMemberByMemberId(memberId);

        Channel channel = channelRepository.findAllByMemberIdAndType(ChannelCode.MAIL,
                member.getId());
        channelRepository.delete(channel);
    }
}
