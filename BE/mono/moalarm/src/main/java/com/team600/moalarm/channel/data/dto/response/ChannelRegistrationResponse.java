package com.team600.moalarm.channel.data.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@ToString
public class ChannelRegistrationResponse {

    private String type;
    private boolean possession;
}
