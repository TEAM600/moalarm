package com.team600.moalarm.alarm.service;

import com.team600.moalarm.alarm.dto.request.AlarmRequirementDto;

public interface AlarmSenderService {
    public void sendAlarms(AlarmRequirementDto requirementDto);
}
