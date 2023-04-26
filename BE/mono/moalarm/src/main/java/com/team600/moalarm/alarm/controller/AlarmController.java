package com.team600.moalarm.alarm.controller;

import com.team600.moalarm.alarm.dto.request.SendAlarmRequest;
import com.team600.moalarm.alarm.service.SenderService;
import com.team600.moalarm.channel.data.code.ChannelCode;
import com.team600.moalarm.channel.data.dto.ChannelKeyDto;
import com.team600.moalarm.channel.service.ChannelService;
import java.util.Map;
import java.util.Map.Entry;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/notification")
public class AlarmController {
    private final ChannelService channelService;
    private final Map<String, SenderService> senderService;

    @PostMapping
    public ResponseEntity<Void> sendNotification(@RequestBody SendAlarmRequest requirementDto) {
        String moalarmKey = "";
        log.info("POST /notification : {}", moalarmKey);
        log.info("{}", senderService);
        Map<ChannelCode, ChannelKeyDto> channels = channelService.getChannelKeyList(moalarmKey);
        for (Entry channel:channels.entrySet()) {
            ChannelCode type = (ChannelCode) channel.getKey();
            if (type == ChannelCode.SMS) {
                senderService.get("smsSenderServiceImpl").send(requirementDto,
                        (ChannelKeyDto) channel.getValue());
            } else if(type == ChannelCode.MAIL) {
                senderService.get("mailSenderServiceImpl").send(requirementDto,
                        (ChannelKeyDto) channel.getValue());
            }
        }
        return ResponseEntity.ok().build();
    }

}
