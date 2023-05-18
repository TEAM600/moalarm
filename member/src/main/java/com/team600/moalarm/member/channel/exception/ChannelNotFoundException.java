package com.team600.moalarm.member.channel.exception;

import com.team600.moalarm.member.common.exception.NotFoundException;

public class ChannelNotFoundException extends NotFoundException {

    public ChannelNotFoundException() {
        super("해당 채널을 찾을 수 없습니다.");
    }
}
