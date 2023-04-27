package com.team600.moalarm.member.exception;

import com.team600.moalarm.common.exception.NotFoundException;

public class MoalarmKeyNotFoundException extends NotFoundException {

    public MoalarmKeyNotFoundException() {
        super("해당 키와 일치하는 사용자가 없습니다.\n request header를 확인해주세요.\n Authorization: MoalarmKey {moalarm-key}");
    }
}
