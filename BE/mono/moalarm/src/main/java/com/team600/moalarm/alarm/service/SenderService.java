package com.team600.moalarm.alarm.service;

import com.team600.moalarm.alarm.dto.request.SendAlarmRequest;
import com.team600.moalarm.channel.data.dto.ChannelKeyDto;

public interface SenderService {
    void send(SendAlarmRequest requirementDto, ChannelKeyDto channelKeyDto);

}
