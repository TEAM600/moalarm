package com.team600.moalarm.member.channel.data.dto;

import com.team600.moalarm.member.channel.data.code.ChannelCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ChannelKeyDto {

    private ChannelCode type;
    private String apiKey; // email, apikey
    private String secret; // secretKey, password
    private String extraValue;
}
