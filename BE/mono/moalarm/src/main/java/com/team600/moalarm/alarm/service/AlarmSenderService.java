package com.team600.moalarm.alarm.service;

import com.team600.moalarm.alarm.dto.request.SendAlarmRequest;

public interface AlarmSenderService {
    void sendAlarms(SendAlarmRequest requirementDto, String moalarmKey);
}
