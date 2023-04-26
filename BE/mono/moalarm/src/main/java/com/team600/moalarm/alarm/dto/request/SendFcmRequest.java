package com.team600.moalarm.alarm.dto.request;

import java.util.List;

public class SendFcmRequest {

    private List<String> to;
    private String content;
    private String title;
    private String img;
}
