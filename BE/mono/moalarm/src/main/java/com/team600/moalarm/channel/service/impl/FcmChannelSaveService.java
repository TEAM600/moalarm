package com.team600.moalarm.channel.service.impl;

import com.team600.moalarm.channel.data.dto.request.ChannelCreateRequest;
import com.team600.moalarm.channel.data.repository.ChannelRepository;
import com.team600.moalarm.channel.service.ChannelSaveService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Slf4j
@Service
public class FcmChannelSaveService implements ChannelSaveService {

    private final ChannelRepository channelRepository;

    @Override
    @Transactional
    public void saveChannel(ChannelCreateRequest requestDto, String memberId) {
        log.info("FCM Channel Save");
    }
}
