package com.team600.moalarm.gateway.common.exception;

public abstract class NotFoundException extends CustomException{

    public NotFoundException(String message) {
        super(message);
    }
}
