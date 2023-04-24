package com.team600.moalarm.alarm.dto.request;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AlarmRequirementDto {
    private MailRequirementDto mail;
    private SmsRequirementDto sms;
    private FcmRequirementDto push;

}
