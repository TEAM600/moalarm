package com.team600.moalarm.auth.common.exception;

public abstract class BadRequestException extends CustomException {

    public BadRequestException(String message) {
        super(message);
    }
}
