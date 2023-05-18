package com.team600.moalarm.member.member.exception;

import com.team600.moalarm.member.common.exception.NotFoundException;

public class MoalarmKeyNotFoundException extends NotFoundException {

    public MoalarmKeyNotFoundException() {
        super("Access Denied");
    }
}
