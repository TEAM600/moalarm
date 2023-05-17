package com.team600.moalarm.gateway.common.exception;

public abstract class CustomException extends RuntimeException{

    public CustomException(String message) {
        super(message);
    }
}
