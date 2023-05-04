package com.team600.moalarm.channel.service.impl;

import com.team600.moalarm.alarm.dto.request.SendAlarmRequest;
import com.team600.moalarm.alarm.service.SenderService;
import com.team600.moalarm.channel.data.code.ChannelCode;
import com.team600.moalarm.channel.data.dto.ChannelKeyDto;
import com.team600.moalarm.channel.data.dto.response.ChannelRegistrationResponse;
import com.team600.moalarm.channel.data.entity.Channel;
import com.team600.moalarm.channel.data.repository.ChannelRepository;
import com.team600.moalarm.channel.service.ChannelService;
import com.team600.moalarm.common.component.MemberUtil;
import com.team600.moalarm.member.entity.Member;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
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
    private final Map<String, SenderService> senderService;

    @Override
    @Transactional(readOnly = true)
    public List<ChannelRegistrationResponse> getChannels(long memberId) {
        Member member = memberUtil.getMemberByMemberId(memberId);

        //TODO: get Channels
        List<ChannelRegistrationResponse> responseDto = new ArrayList<>();
        return responseDto;
    }

    @Override
    @Transactional(readOnly = true)
    public void sendAlarm(long memberId, SendAlarmRequest requirementDto) {
        Member member = memberUtil.getMemberByMemberId(memberId);

        List<Channel> channels = channelRepository.findAllByMemberId(member.getId());

        channels.forEach(channel -> {
            ChannelCode type = channel.getType();
            ChannelKeyDto channelKeyDto = new ChannelKeyDto();
            channelKeyDto.setType(type);
            if (type == ChannelCode.FCM) {
                //TODO: 제이슨 구현 후 FILE String 넣기
                channelKeyDto.setJson("{}");
            } else if (type == ChannelCode.SMS || type == ChannelCode.MAIL) {
                channelKeyDto.setApiKey(channel.getApiKey());
                channelKeyDto.setSecret(channel.getSecret());
                channelKeyDto.setPhoneNumber(channel.getExtraValue());
            }
            senderService.get(type.getValue() + "SenderServiceImpl")
                    .send(memberId, requirementDto, channelKeyDto);
        });

    }

    @Override
    @Transactional
    public void deleteChannel(ChannelCode type, long memberId) {
        Member member = memberUtil.getMemberByMemberId(memberId);

        Channel channel = channelRepository.findAllByMemberIdAndType(type, member.getId());
        channel.remove();
        member.deleteChannel(type.ordinal());
    }
}
