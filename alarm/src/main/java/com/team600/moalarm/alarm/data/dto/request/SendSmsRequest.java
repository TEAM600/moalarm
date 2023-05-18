package com.team600.moalarm.alarm.data.dto.request;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SendSmsRequest {
    private List<String> to;
    private String content;
}
