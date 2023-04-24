package com.team600.moalarm.auth.exception;

import com.team600.moalarm.common.exception.BadRequestException;

public class SignInFailedException extends BadRequestException {

    public SignInFailedException() {
        super("로그인에 실패했습니다");
    }
}
