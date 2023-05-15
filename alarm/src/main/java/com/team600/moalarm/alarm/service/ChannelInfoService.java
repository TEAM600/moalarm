package com.team600.moalarm.alarm.service;

import com.team600.moalarm.alarm.data.dto.request.SendAlarmRequest;
import java.io.IOException;

public interface ChannelInfoService {
    public void sendAlarm(String moalarmKey, SendAlarmRequest alarmRequest) throws IOException;
}
