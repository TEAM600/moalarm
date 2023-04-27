package com.team600.moalarm.channel.service.impl;

import com.team600.moalarm.channel.data.code.ChannelCode;
import com.team600.moalarm.channel.data.dto.ChannelKeyDto;
import com.team600.moalarm.channel.data.dto.response.ChannelPossessionResponse;
import com.team600.moalarm.channel.data.entity.Channel;
import com.team600.moalarm.channel.data.repository.ChannelRepository;
import com.team600.moalarm.channel.service.ChannelService;
import com.team600.moalarm.common.component.MemberUtil;
import com.team600.moalarm.member.entity.Member;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class ChannelServiceImpl implements ChannelService {

    private final ChannelRepository channelRepository;
    private final MemberUtil memberComponent;

    @Override
    @Transactional(readOnly = true)
    public List<ChannelPossessionResponse> getPossessions(String memberId) {
        Member member = memberComponent.getMemberByMemberId(memberId);

        //TODO: get Channels
        List<ChannelPossessionResponse> responseDto = new ArrayList<>();
        return responseDto;
    }

    @Override
    @Transactional(readOnly = true)
    public Map<ChannelCode, ChannelKeyDto> getChannelKeyList(String moalarmKey) {
        Member member = memberComponent.getMemberMoalarmKey(moalarmKey);

        List<Channel> channels = channelRepository.findAllByMemberId(member.getId());

        Map<ChannelCode, ChannelKeyDto> returnDto = new HashMap<>();

        channels.forEach(channel -> {
            ChannelCode type = channel.getType();
            ChannelKeyDto channelKeyDto = new ChannelKeyDto();
            channelKeyDto.setType(type);
            if (type == ChannelCode.FCM) {
                //TODO: 제이슨 구현 후 FILE String 넣기
                channelKeyDto.setJson("{}");
            } else if (type == ChannelCode.SMS || type == ChannelCode.MAIL) {
                channelKeyDto.setApiKey(channel.getApiKey());
                channelKeyDto.setSecret(channelKeyDto.getSecret());
                if (type == ChannelCode.SMS) {
                    channelKeyDto.setPhoneNumber("");
                }
            } else {
                throw new RuntimeException("서버 내부 에러");
            }
            returnDto.put(type, channelKeyDto);
        });

        return returnDto;
    }
}
