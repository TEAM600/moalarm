package com.team600.moalarm.member.exception;

import com.team600.moalarm.common.exception.NotFoundException;

public class MemberNotFoundException extends NotFoundException {

    public MemberNotFoundException(String message) {
        super(message);
    }
}
