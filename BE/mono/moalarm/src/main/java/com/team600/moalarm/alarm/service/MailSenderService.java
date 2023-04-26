package com.team600.moalarm.alarm.service;

import com.team600.moalarm.alarm.dto.request.SendMailRequest;
import com.team600.moalarm.alarm.vo.SendMailVo;

public interface MailSenderService {
    void sendEmail(SendMailRequest requirementDto, SendMailVo sendMailVo);
}
