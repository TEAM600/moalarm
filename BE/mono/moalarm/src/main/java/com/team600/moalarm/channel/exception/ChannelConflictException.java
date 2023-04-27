package com.team600.moalarm.channel.exception;

import com.team600.moalarm.common.exception.ConflictException;

public class ChannelConflictException extends ConflictException {

    public ChannelConflictException(String message) {
        super(message);
    }
}
