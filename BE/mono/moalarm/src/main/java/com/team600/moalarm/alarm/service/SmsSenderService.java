package com.team600.moalarm.alarm.service;

import com.team600.moalarm.alarm.dto.request.SendSmsRequest;
import com.team600.moalarm.alarm.vo.SendSmsVo;

public interface SmsSenderService {
    void sendSms(SendSmsRequest requirementDto, SendSmsVo sendSmsVo);
}
