package com.team600.moalarm.channel.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@ToString
public class ChannelPossessionDto {
    private String type;
    private boolean possession;
}
