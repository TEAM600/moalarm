package com.team600.moalarm.channel.service;

import com.team600.moalarm.channel.dto.request.ChannelDto;
import com.team600.moalarm.channel.dto.response.ChannelPossessionDto;
import java.util.List;

public interface ChannelSaveService {
    void saveChannel(ChannelDto requestDto, String memberId);
}
