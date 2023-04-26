package com.team600.moalarm.channel.service.impl;

import com.team600.moalarm.channel.data.code.ChannelCode;
import com.team600.moalarm.channel.data.dto.ChannelKeyDto;
import com.team600.moalarm.channel.data.dto.response.ChannelPossessionResponse;
import com.team600.moalarm.channel.data.entity.Channel;
import com.team600.moalarm.channel.data.repository.ChannelRepository;
import com.team600.moalarm.channel.service.ChannelService;
import com.team600.moalarm.member.repository.MemberRepository;
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
    private final MemberRepository memberRepository;

    @Override
    @Transactional
    public List<ChannelPossessionResponse> getPossessions(String memberId) {
        List<ChannelPossessionResponse> responseDto = new ArrayList<>(); //TODO: get Channels
        return responseDto;
    }

    @Override
    @Transactional
    public Map<ChannelCode, ChannelKeyDto> getChannelKeyList(String moalarmKey) {
        //TODO: memberRepository 구현 되면 모알람키를 통해 유저 key값을 가져오기
        List<Channel> channels = channelRepository.findAllByMemberId(1L);

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
                    channelKeyDto.setPhoneNumber("01000000000");
                }
            } else {
                throw new RuntimeException("서버 내부 에러");
            }
            returnDto.put(type, channelKeyDto);
        });

        return returnDto;
    }


}
