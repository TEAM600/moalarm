package com.team600.moalarm.alarm.exception;

import com.team600.moalarm.alarm.common.exception.BadRequestException;

public class PushSendFailedException extends BadRequestException {

    public PushSendFailedException() {
        super("푸시알림 전송을 실패하였습니다.");
    }
}
