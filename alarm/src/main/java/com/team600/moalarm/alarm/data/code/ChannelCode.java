package com.team600.moalarm.alarm.data.code;

import com.team600.moalarm.alarm.exception.ChannelTypeNotValidException;
import lombok.Getter;

@Getter
public enum ChannelCode {
    SMS("sms"), MAIL("mail"), PUSH("push");
    private final String value;

    ChannelCode(String type) {
        this.value = type;
    }

    // 생성자 생략

    public static ChannelCode of(String codeStr) {
        if (codeStr == null) {
            throw new IllegalArgumentException();
        }

        for (ChannelCode channelCode : ChannelCode.values()) {
            if (channelCode.value.equals(codeStr)) {
                return channelCode;
            }
        }

        throw new ChannelTypeNotValidException("codeStr");
    }
}
