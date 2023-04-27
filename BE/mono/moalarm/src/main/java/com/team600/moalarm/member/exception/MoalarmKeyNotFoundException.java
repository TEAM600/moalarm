package com.team600.moalarm.member.exception;

import com.team600.moalarm.common.exception.NotFoundException;

public class MoalarmKeyNotFoundException extends NotFoundException {

    public MoalarmKeyNotFoundException() {
        super("Access Denied");
    }
}
