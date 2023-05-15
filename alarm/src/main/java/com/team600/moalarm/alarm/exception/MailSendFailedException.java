package com.team600.moalarm.alarm.exception;

import com.team600.moalarm.alarm.common.exception.BadRequestException;
public class MailSendFailedException extends BadRequestException {

    public MailSendFailedException() {
        super("메일 전송을 실패하였습니다.");
    }
}
