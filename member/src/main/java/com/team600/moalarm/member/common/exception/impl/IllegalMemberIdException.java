package com.team600.moalarm.member.common.exception.impl;

import com.team600.moalarm.member.common.exception.BadRequestException;

public class IllegalMemberIdException extends BadRequestException {

    public IllegalMemberIdException(String memberId) {
        super(memberId + "는 올바른 id 형식이 아닙니다.");
    }
}
