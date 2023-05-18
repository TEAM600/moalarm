package com.team600.moalarm.member.channel.data.dto;

import com.team600.moalarm.member.channel.data.code.ChannelCode;
import com.team600.moalarm.member.channel.data.entity.Channel;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class ChannelKeyDto {

    private ChannelCode type;
    private String apiKey; // email, apikey
    private String secret; // secretKey, password
    private String extraValue;

    public static ChannelKeyDto of(Channel channel) {
        return ChannelKeyDto.builder()
                .type(channel.getType())
                .apiKey(channel.getApiKey())
                .secret(channel.getSecret())
                .extraValue(channel.getExtraValue())
                .build();
    }
}
