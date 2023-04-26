package com.team600.moalarm.alarm.dto.request;

import java.util.List;
import lombok.Getter;

@Getter
public class SendSmsRequest {
    private List<String> to;
    private String content;
}
