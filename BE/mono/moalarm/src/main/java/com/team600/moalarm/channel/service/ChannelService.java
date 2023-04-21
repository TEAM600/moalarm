package com.team600.moalarm.channel.service;

import com.team600.moalarm.channel.dto.request.FcmMailChannelDto;
import com.team600.moalarm.channel.dto.request.MailChannelDto;
import com.team600.moalarm.channel.dto.request.SmsChannelDto;
import com.team600.moalarm.channel.dto.response.ChannelPossessionDto;
import java.util.List;

public interface ChannelService {
    List<ChannelPossessionDto> getPossessions(String memberId);

    void saveMailChannel(MailChannelDto requestDto, String memberId);

    void saveSmsChannel(SmsChannelDto requestDto, String memberId);

    void saveFcmChannel(FcmMailChannelDto requestDto, String memberId);
}
