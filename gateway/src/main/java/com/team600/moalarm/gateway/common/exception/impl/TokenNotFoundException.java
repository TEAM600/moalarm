package com.team600.moalarm.gateway.common.exception.impl;

import com.team600.moalarm.gateway.common.exception.NotFoundException;

public class TokenNotFoundException extends NotFoundException {

    public TokenNotFoundException() {
        super("토큰이 담겨있지 않습니다.");
    }
}
