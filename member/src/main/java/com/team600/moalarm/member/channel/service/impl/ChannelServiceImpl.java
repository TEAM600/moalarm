package com.team600.moalarm.member.channel.service.impl;

import com.team600.moalarm.member.channel.data.code.ChannelCode;
import com.team600.moalarm.member.channel.data.dto.ChannelKeyDto;
import com.team600.moalarm.member.channel.data.dto.response.ChannelRegistrationResponse;
import com.team600.moalarm.member.channel.data.dto.response.ChannelsSecretResponse;
import com.team600.moalarm.member.channel.data.entity.Channel;
import com.team600.moalarm.member.channel.data.repository.ChannelRepository;
import com.team600.moalarm.member.channel.exception.ChannelNotFoundException;
import com.team600.moalarm.member.channel.service.ChannelService;
import com.team600.moalarm.member.common.utils.MemberUtil;
import com.team600.moalarm.member.member.entity.Member;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Slf4j
@Service
public class ChannelServiceImpl implements ChannelService {

    private final ChannelRepository channelRepository;
    private final MemberUtil<Long> memberUtil;

    @Transactional(readOnly = true)
    @Override
    public List<ChannelRegistrationResponse> getChannels(long memberId) {
        //TODO 23.05.11
        return null;
    }

    @Transactional(readOnly = true)
    @Override
    public ChannelsSecretResponse<?> getChannelsSecret(String moalarmKey) {
        Member member = memberUtil.getMemberMoalarmKey(moalarmKey);
        List<Channel> channels = channelRepository.findAllByMemberId(member.getId());
        List<ChannelKeyDto> channelKeys = channels.stream()
                .map(ChannelKeyDto::of)
                .collect(Collectors.toList());
        return new ChannelsSecretResponse<>(member.getId(), channelKeys);
    }

    @Transactional
    @Override
    public void deleteChannel(ChannelCode type, long memberId) {
        Member member = memberUtil.getMemberByMemberId(memberId);

        Channel channel = channelRepository.findByMemberIdAndType(member.getId(), type)
                .orElseThrow(ChannelNotFoundException::new);

        channel.remove();
        member.deleteChannel(type.ordinal());
    }
}
