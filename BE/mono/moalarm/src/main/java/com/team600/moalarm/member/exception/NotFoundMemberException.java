package com.team600.moalarm.member.exception;

import com.team600.moalarm.common.exception.NotFoundException;

public class NotFoundMemberException extends NotFoundException {

    public NotFoundMemberException() {
        super("존재하지 않는 회원입니다.");
    }
}
