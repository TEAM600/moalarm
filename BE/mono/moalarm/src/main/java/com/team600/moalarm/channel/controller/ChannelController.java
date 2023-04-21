package com.team600.moalarm.channel.controller;
import com.team600.moalarm.channel.dto.request.FcmMailChannelDto;
import com.team600.moalarm.channel.dto.request.MailChannelDto;
import com.team600.moalarm.channel.dto.request.SmsChannelDto;
import com.team600.moalarm.channel.dto.response.ChannelPossessionDto;
import com.team600.moalarm.channel.service.ChannelService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/channels")
public class ChannelController {
    private final ChannelService channelService;

    //TODO: memberId 전부 담아줘야함
    @GetMapping
    public ResponseEntity<List<ChannelPossessionDto>> getChannels() {
        log.info("GET /channels");
        String memberId = null;
        return ResponseEntity.ok(channelService.getPossessions(memberId));
    }

    //TODO: login, repository, entity, service 작성 후 완성시키기
    @PostMapping("/mail")
    public ResponseEntity<Void> createMailChannel(@RequestBody MailChannelDto requestDto) {
        log.info("POST /channels/mail");
        String memberId = null;
        channelService.saveMailChannel(requestDto, memberId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/sms")
    public ResponseEntity<Void> createSmsChannel(@RequestBody SmsChannelDto requestDto) {
        log.info("POST /channels/mail");
        String memberId = null;
        channelService.saveSmsChannel(requestDto, memberId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/fcm")
    public ResponseEntity<Void> createFcmChannel(@RequestBody FcmMailChannelDto requestDto) {
        log.info("POST /channels/fcm");
        String memberId = null;
        channelService.saveFcmChannel(requestDto, memberId);
        return ResponseEntity.ok().build();
    }
}
