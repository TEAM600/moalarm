package com.team600.moalarm.alarm.data.dto.request;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ChannelsSecretRequest {
    private long memberId;
    private List<ChannelKeyDto> channelKeys;
}
