package com.team600.moalarm.channel.service.impl;

import com.team600.moalarm.channel.dto.request.ChannelDto;
import com.team600.moalarm.channel.dto.request.FcmMailChannelDto;
import com.team600.moalarm.channel.dto.request.MailChannelDto;
import com.team600.moalarm.channel.dto.request.SmsChannelDto;
import com.team600.moalarm.channel.dto.response.ChannelPossessionDto;
import com.team600.moalarm.channel.service.ChannelSaveService;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Slf4j
@Service
public class MailChannelSaveService implements ChannelSaveService {

    @Override
    public void saveChannel(ChannelDto requestDto, String memberId) {
        log.info("Mail Channel Save");
    }
}
