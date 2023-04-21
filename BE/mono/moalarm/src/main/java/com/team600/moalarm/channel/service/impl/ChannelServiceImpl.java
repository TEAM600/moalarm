package com.team600.moalarm.channel.service.impl;

import com.team600.moalarm.channel.dto.request.FcmMailChannelDto;
import com.team600.moalarm.channel.dto.request.MailChannelDto;
import com.team600.moalarm.channel.dto.request.SmsChannelDto;
import com.team600.moalarm.channel.dto.response.ChannelPossessionDto;
import com.team600.moalarm.channel.service.ChannelService;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ChannelServiceImpl implements ChannelService {

    @Override
    public List<ChannelPossessionDto> getPossessions(String memberId) {
        List<ChannelPossessionDto> responseDto = new ArrayList<>(); //TODO: get Channels
        return responseDto;
    }

    // TODO: 레포구현 후 Save 구현
    @Override
    public void saveMailChannel(MailChannelDto requestDto, String memberId) {

    }

    @Override
    public void saveSmsChannel(SmsChannelDto requestDto, String memberId) {

    }

    @Override
    public void saveFcmChannel(FcmMailChannelDto requestDto, String memberId) {

    }
}
