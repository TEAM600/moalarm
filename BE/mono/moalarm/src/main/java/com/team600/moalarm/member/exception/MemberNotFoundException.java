package com.team600.moalarm.member.exception;

import com.team600.moalarm.common.exception.NotFoundException;

public class MemberNotFoundException extends NotFoundException {

    public MemberNotFoundException() {
        super("존재하지 않는 유저입니다.");
    }
}
