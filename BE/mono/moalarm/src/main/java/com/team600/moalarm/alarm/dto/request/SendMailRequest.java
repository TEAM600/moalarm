package com.team600.moalarm.alarm.dto.request;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SendMailRequest {
    private List<String> to;
    private String content;
    private String title;
}
