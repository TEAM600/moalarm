package com.team600.moalarm.member.channel.data.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChannelCreateRequest {

    private String key;
    private String secret;
    private String phone;
    private String extraValue;
}
