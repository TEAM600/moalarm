package com.team600.moalarm.member.common.exception.impl;

import com.team600.moalarm.member.common.exception.BadRequestException;

public class MissingMemberIdHeaderException extends BadRequestException {

    public MissingMemberIdHeaderException() {
        super("Member-Id header가 없습니다.");
    }
}
