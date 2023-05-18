package com.team600.moalarm.member.channel.exception;

import com.team600.moalarm.member.common.exception.ConflictException;

public class ChannelConflictException extends ConflictException {

    public ChannelConflictException() {
        super("이미 채널을 소유중 입니다.");
    }
}
