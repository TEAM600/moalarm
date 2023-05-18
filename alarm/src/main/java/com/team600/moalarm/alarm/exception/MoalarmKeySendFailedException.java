package com.team600.moalarm.alarm.exception;

import com.team600.moalarm.alarm.common.exception.BadRequestException;

public class MoalarmKeySendFailedException extends BadRequestException {
    public MoalarmKeySendFailedException() {
        super("Member 서버 접근에 실패하였습니다.");
    }
}
