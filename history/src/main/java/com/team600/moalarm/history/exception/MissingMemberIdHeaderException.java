package com.team600.moalarm.history.exception;

public class MissingMemberIdHeaderException extends BadRequestException {

    public MissingMemberIdHeaderException() {
        super("Member-Id header가 없습니다.");
    }
}