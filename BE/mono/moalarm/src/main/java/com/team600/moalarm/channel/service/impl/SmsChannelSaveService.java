package com.team600.moalarm.channel.service.impl;

import com.team600.moalarm.channel.dto.request.ChannelDto;
import com.team600.moalarm.channel.service.ChannelSaveService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Slf4j
@Service
public class SmsChannelSaveService implements ChannelSaveService {
    @Override
    public void saveChannel(ChannelDto requestDto, String memberId) {
        log.info("SMS Channel Save");
    }
}
