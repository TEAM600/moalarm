package com.team600.moalarm.channel.service;

import com.team600.moalarm.channel.data.dto.request.ChannelCreateRequest;

public interface ChannelSaveService {

    void saveChannel(ChannelCreateRequest requestDto, long memberId);
}
