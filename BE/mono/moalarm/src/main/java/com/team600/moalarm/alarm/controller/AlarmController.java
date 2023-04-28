package com.team600.moalarm.alarm.controller;

import com.team600.moalarm.alarm.dto.request.SendAlarmRequest;
import com.team600.moalarm.channel.service.ChannelService;
import com.team600.moalarm.common.annotation.CurrentMemberId;
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

    @PostMapping
    public ResponseEntity<Void> sendNotification(@RequestBody SendAlarmRequest requirementDto,
            @CurrentMemberId long memberId) {
        log.info("POST /notification : {}", memberId);
        channelService.sendAlarm(memberId, requirementDto);
        return ResponseEntity.ok().build();
    }

}
