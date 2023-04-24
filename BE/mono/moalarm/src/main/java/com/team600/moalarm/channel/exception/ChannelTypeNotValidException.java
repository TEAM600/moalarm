package com.team600.moalarm.channel.exception;

import com.team600.moalarm.common.exception.BadRequestException;

public class ChannelTypeNotValidException extends BadRequestException {

    public ChannelTypeNotValidException() {
        super("올바르지 않은 타입값이 들어왔습니다.\ntype은 sms, fcm, mail중 하나여야 합니다.");
    }
}
