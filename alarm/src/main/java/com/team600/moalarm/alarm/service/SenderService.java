package com.team600.moalarm.alarm.service;

import com.team600.moalarm.alarm.data.dto.request.ChannelKeyDto;
import com.team600.moalarm.alarm.data.dto.request.SendAlarmRequest;

public interface SenderService {

    void send(long memberId, SendAlarmRequest requirementDto, ChannelKeyDto channelKeyDto);

}
