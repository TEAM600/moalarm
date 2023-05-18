package com.team600.moalarm.alarm.data.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SendAlarmRequest {

    private SendMailRequest mail;
    private SendSmsRequest sms;
    private SendPushRequest push;

}
