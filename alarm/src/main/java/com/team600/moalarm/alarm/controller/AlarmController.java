package com.team600.moalarm.alarm.controller;

import com.team600.moalarm.alarm.data.dto.request.SendAlarmRequest;
import com.team600.moalarm.alarm.service.ChannelInfoService;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/notification")
public class AlarmController {

    private final ChannelInfoService getChannelsSecrets;

    @PostMapping
    public ResponseEntity<Void> sendNotification(@RequestBody SendAlarmRequest requirementDto,
            @RequestHeader("Authorization") String moalarmKey) throws IOException {
        log.info("POST /notification : {}", moalarmKey);
        getChannelsSecrets.sendAlarm(moalarmKey, requirementDto);
        return ResponseEntity.ok().build();
    }

}
