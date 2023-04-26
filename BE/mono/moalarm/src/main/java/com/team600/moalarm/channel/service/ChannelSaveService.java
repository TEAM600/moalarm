package com.team600.moalarm.channel.service;

import com.team600.moalarm.channel.data.dto.request.ChannelRequest;

public interface ChannelSaveService {

    void saveChannel(ChannelRequest requestDto, String memberId);
}
