package com.team600.moalarm.channel.controller;

import com.team600.moalarm.channel.dto.request.ChannelDto;
import com.team600.moalarm.channel.dto.response.ChannelPossessionDto;
import com.team600.moalarm.channel.exception.ChannelTypeNotValidException;
import com.team600.moalarm.channel.service.ChannelSaveService;
import com.team600.moalarm.channel.service.ChannelService;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/channels")
public class ChannelController {

    private final ChannelService channelService;
    private final Map<String, ChannelSaveService> channelSaveService;

    //TODO: memberId 전부 담아줘야함
    @GetMapping
    public ResponseEntity<List<ChannelPossessionDto>> getChannels() {
        log.info("GET /channels");
        String memberId = null;
        return ResponseEntity.ok(channelService.getPossessions(memberId));
    }

    //TODO: login, repository, entity, service 작성 후 완성시키기
    @PostMapping("/type/{type}")
    public ResponseEntity<Void> createChannel(@PathVariable("type") String type, @RequestBody ChannelDto requestDto) {
        log.info("POST /channels/mail");
        String memberId = null;
        switch (type) { //TODO: 채널별 DTO 유효성 검사
            case "sms":
            case "fcm":
            case "mail":
                channelSaveService.get(type + "ChannelSaveService").saveChannel(requestDto, memberId);
                break;
            default:
                throw new ChannelTypeNotValidException();
        }
        return ResponseEntity.ok().build();
    }

}
