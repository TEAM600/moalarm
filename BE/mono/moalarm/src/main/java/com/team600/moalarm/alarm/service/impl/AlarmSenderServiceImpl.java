package com.team600.moalarm.alarm.service.impl;

import com.team600.moalarm.alarm.dto.request.SendAlarmRequest;
import com.team600.moalarm.alarm.service.AlarmSenderService;
import com.team600.moalarm.alarm.service.MailSenderService;
import com.team600.moalarm.alarm.service.SmsSenderService;
import com.team600.moalarm.alarm.vo.SendMailVo;
import com.team600.moalarm.alarm.vo.SendSmsVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class AlarmSenderServiceImpl implements AlarmSenderService {
    private final MailSenderService mailSenderService;
    private final SmsSenderService smsSenderService;

    @Override
    public void sendAlarms(SendAlarmRequest requirementDto, String moalarmKey) {
        SendMailVo sendMailVo = new SendMailVo();
        SendSmsVo sendSmsVo = new SendSmsVo();
        //TODO: moalarmKey로 채널들 정보 갖고와서 넘겨주기
        mailSenderService.sendEmail(requirementDto.getMail(), sendMailVo);
        smsSenderService.sendSms(requirementDto.getSms(), sendSmsVo);
    }
}
