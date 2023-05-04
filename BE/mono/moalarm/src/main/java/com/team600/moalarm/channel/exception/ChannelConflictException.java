package com.team600.moalarm.channel.exception;

import com.team600.moalarm.common.exception.ConflictException;

public class ChannelConflictException extends ConflictException {

    public ChannelConflictException() {
        super("이미 채널을 소유중 입니다.");
    }
}
