package com.team600.moalarm.gateway.common.exception.impl;

import com.team600.moalarm.gateway.common.exception.BadRequestException;

public class TokenValidateException extends BadRequestException {

    public TokenValidateException() {
        super("유효하지 않은 토큰입니다.");
    }
}