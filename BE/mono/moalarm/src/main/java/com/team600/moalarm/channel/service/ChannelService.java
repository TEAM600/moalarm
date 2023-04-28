package com.team600.moalarm.channel.service;

import com.team600.moalarm.alarm.dto.request.SendAlarmRequest;
import com.team600.moalarm.channel.data.code.ChannelCode;
import com.team600.moalarm.channel.data.dto.response.ChannelRegistrationResponse;
import java.util.List;

public interface ChannelService {

    List<ChannelRegistrationResponse> getChannels(long memberId);

    void getChannelKeyList(String moalarmKey,
            SendAlarmRequest requirementDto);

    void deleteChannel(ChannelCode type, long memberId);
}
