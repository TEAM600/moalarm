package com.team600.moalarm.member.exception;

import com.team600.moalarm.common.exception.ConflictException;

public class EmailConflictException extends ConflictException {

    public EmailConflictException() {
        super("이미 존재하는 이메일입니다.");
    }
}
