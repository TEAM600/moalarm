package com.team600.moalarm.alarm.controller;

import com.team600.moalarm.alarm.dto.request.SendAlarmRequest;
import com.team600.moalarm.alarm.service.AlarmSenderService;
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
    private final AlarmSenderService mailSenderService;

    @PostMapping
    public ResponseEntity<Void> sendNotification(@RequestBody SendAlarmRequest requirementDto) {
        String moalarmKey = "";
        log.info("POST /notification : {}", moalarmKey);
        mailSenderService.sendAlarms(requirementDto, moalarmKey);
        return ResponseEntity.ok().build();
    }
}
