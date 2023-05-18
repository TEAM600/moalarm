package com.team600.moalarm.member.channel.data.dto.response;

import com.team600.moalarm.member.channel.data.dto.ChannelKeyDto;
import java.io.Serializable;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ChannelsSecretResponse<ID extends Serializable> {

    private ID memberId;
    private List<ChannelKeyDto> channelKeys;
}
