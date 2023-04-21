package com.team600.moalarm.channel.service.impl;

import com.team600.moalarm.channel.dto.response.ChannelPossessionDto;
import com.team600.moalarm.channel.service.ChannelService;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ChannelServiceImpl implements ChannelService {

    @Override
    public List<ChannelPossessionDto> getPossessions(String memberId) {
        List<ChannelPossessionDto> responseDto = new ArrayList<>(); //TODO: get Channels
        return responseDto;
    }
}
