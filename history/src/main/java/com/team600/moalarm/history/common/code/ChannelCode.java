package com.team600.moalarm.history.common.code;

import com.team600.moalarm.history.exception.ChannelTypeNotValidException;
import lombok.Getter;

@Getter
public enum ChannelCode {
    SMS("sms"), MAIL("mail"), FCM("push");
    private final String value;

    ChannelCode(String type) {
        this.value = type;
    }

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

