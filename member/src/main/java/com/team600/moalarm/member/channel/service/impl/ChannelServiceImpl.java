package com.team600.moalarm.member.channel.service.impl;

import com.team600.moalarm.member.channel.data.code.ChannelCode;
import com.team600.moalarm.member.channel.data.dto.response.ChannelRegistrationResponse;
import com.team600.moalarm.member.channel.data.entity.Channel;
import com.team600.moalarm.member.channel.data.repository.ChannelRepository;
import com.team600.moalarm.member.channel.exception.ChannelNotFoundException;
import com.team600.moalarm.member.channel.service.ChannelService;
import com.team600.moalarm.member.common.component.MemberUtil;
import com.team600.moalarm.member.member.entity.Member;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Slf4j
@Service
public class ChannelServiceImpl implements ChannelService {

    private final ChannelRepository channelRepository;
    private final MemberUtil memberUtil;

    @Override
    @Transactional(readOnly = true)
    public List<ChannelRegistrationResponse> getChannels(long memberId) {
        Member member = memberUtil.getMemberByMemberId(memberId);

        //TODO: get Channels
        List<ChannelRegistrationResponse> responseDto = new ArrayList<>();
        return responseDto;
    }

    @Override
    @Transactional
    public void deleteChannel(ChannelCode type, long memberId) {
        Member member = memberUtil.getMemberByMemberId(memberId);

        Channel channel = channelRepository.findByMemberIdAndType(member.getId(), type)
                .orElseThrow(ChannelNotFoundException::new);

        channel.remove();
        member.deleteChannel(type.ordinal());
    }
}
