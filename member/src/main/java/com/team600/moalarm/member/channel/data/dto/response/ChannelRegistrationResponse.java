package com.team600.moalarm.member.channel.data.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@ToString
@Builder
public class ChannelRegistrationResponse {

    private String type;
    private boolean registration;
}
