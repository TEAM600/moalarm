package com.team600.moalarm.channel.exception;

import com.team600.moalarm.common.exception.NotFoundException;

public class ChannelNotFoundException extends NotFoundException {

    public ChannelNotFoundException() {
        super("해당 채널을 찾을 수 없습니다.");
    }
}
