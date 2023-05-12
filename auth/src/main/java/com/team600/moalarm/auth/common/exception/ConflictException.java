package com.team600.moalarm.auth.common.exception;

public abstract class ConflictException extends CustomException{

    public ConflictException(String message) {
        super(message);
    }
}
