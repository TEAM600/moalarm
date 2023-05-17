package com.team600.moalarm.alarm.service;

import com.team600.moalarm.alarm.data.dto.response.ChannelKeyDto;
import com.team600.moalarm.alarm.data.dto.request.SendAlarmRequest;

public interface SenderService {

    void send(long memberId, long alarmRequestId, SendAlarmRequest requirementDto, ChannelKeyDto channelKeyDto);

}
