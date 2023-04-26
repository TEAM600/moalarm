package com.team600.moalarm.channel.service.impl;

import com.team600.moalarm.channel.data.dto.request.ChannelRequest;
import com.team600.moalarm.channel.data.repository.ChannelRepository;
import com.team600.moalarm.channel.service.ChannelSaveService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Slf4j
@Service
public class SmsChannelSaveService implements ChannelSaveService {

    private final ChannelRepository channelRepository;

    @Override
    public void saveChannel(ChannelRequest requestDto, String memberId) {
        log.info("SMS Channel Save");
    }
}
