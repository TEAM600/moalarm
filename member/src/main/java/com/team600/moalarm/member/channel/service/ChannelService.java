package com.team600.moalarm.member.channel.service;

import com.team600.moalarm.member.channel.data.code.ChannelCode;
import com.team600.moalarm.member.channel.data.dto.response.ChannelRegistrationResponse;
import com.team600.moalarm.member.channel.data.dto.response.ChannelsSecretResponse;
import java.util.List;

public interface ChannelService {

    List<ChannelRegistrationResponse> getChannels(long memberId);

    ChannelsSecretResponse<?> getChannelsSecret(String moalarmKey);

    void deleteChannel(ChannelCode type, long memberId);
}
