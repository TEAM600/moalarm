package com.team600.moalarm.channel.service.impl;

import com.team600.moalarm.channel.data.code.ChannelCode;
import com.team600.moalarm.channel.data.dto.request.ChannelRequest;
import com.team600.moalarm.channel.data.entity.Channel;
import com.team600.moalarm.channel.data.repository.ChannelRepository;
import com.team600.moalarm.channel.service.ChannelSaveService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Slf4j
@Service
public class MailChannelSaveService implements ChannelSaveService {

    private final ChannelRepository channelRepository;

    @Override
    @Transactional
    public void saveChannel(ChannelRequest requestDto, String memberId) {
        log.info("Mail Channel Save");
        //TODO: 멤버를 가져오는 동작 또는 id 존재 유무 체크

        Channel channel = Channel.builder()
                .memberId(1)
                .apiKey(requestDto.getKey())
                .secret(requestDto.getSecret())
                .type(ChannelCode.MAIL)
                .build();
        log.info("Mail Channel Save2");
        channelRepository.save(channel);
        log.info("Mail Channel Save3");
    }
}
