package com.team600.moalarm.alarm.dto.request;

import lombok.Getter;

@Getter
public class SendMailRequest extends SendParentRequest {
    private String title;
}
