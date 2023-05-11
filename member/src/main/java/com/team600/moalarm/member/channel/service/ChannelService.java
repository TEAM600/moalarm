package com.team600.moalarm.member.channel.service;

import com.team600.moalarm.member.channel.data.code.ChannelCode;
import com.team600.moalarm.member.channel.data.dto.response.ChannelRegistrationResponse;
import java.util.List;

public interface ChannelService {

    List<ChannelRegistrationResponse> getChannels(long memberId);

    void deleteChannel(ChannelCode type, long memberId);
}
