package com.team600.moalarm.history.exception;

public class ChannelTypeNotValidException extends BadRequestException {

    public ChannelTypeNotValidException(String type) {
        super("올바르지 않은 타입값이 들어왔습니다."
                + "\ninput : " + type
                + "\ntype은 sms, fcm, mail중 하나여야 합니다.");
    }
}
