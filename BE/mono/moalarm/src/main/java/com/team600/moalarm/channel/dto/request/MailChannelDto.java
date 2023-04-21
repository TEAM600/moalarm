package com.team600.moalarm.channel.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MailChannelDto {
    private String email;
    private String secret;
}
