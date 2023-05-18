package com.team600.moalarm.alarm.exception;

import com.team600.moalarm.alarm.common.exception.BadRequestException;

public class HistorySendFailedException extends BadRequestException {
    public HistorySendFailedException() {
        super("History 서버 접근에 실패하였습니다.");
    }
}
