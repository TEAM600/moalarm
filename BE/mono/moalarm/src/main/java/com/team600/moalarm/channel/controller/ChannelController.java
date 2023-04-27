package com.team600.moalarm.channel.controller;

import com.team600.moalarm.channel.data.code.ChannelCode;
import com.team600.moalarm.channel.data.dto.request.ChannelCreateRequest;
import com.team600.moalarm.channel.data.dto.response.ChannelRegistrationResponse;
import com.team600.moalarm.channel.service.ChannelSaveService;
import com.team600.moalarm.channel.service.ChannelService;
import com.team600.moalarm.common.annotation.CurrentMemberId;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/channels")
public class ChannelController {

    private final ChannelService channelService;
    private final Map<String, ChannelSaveService> channelSaveService;

    //TODO: memberId 전부 담아줘야함
    @GetMapping
    public ResponseEntity<List<ChannelRegistrationResponse>> getChannels(
            @CurrentMemberId long memberId) {
        log.info("GET /channels");
        return ResponseEntity.ok(channelService.getChannels(memberId));
    }

    @PostMapping("/{type}")
    public ResponseEntity<Void> createChannel(@PathVariable("type") ChannelCode type,
            @RequestBody ChannelCreateRequest requestDto,
            @CurrentMemberId long memberId) {
        log.info("POST /channels/{}", type);
        //TODO: 채널별 유효성 검사
        channelSaveService.get(type.getValue() + "ChannelService")
                .saveChannel(requestDto, memberId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{type}")
    public ResponseEntity<Void> deleteChannel(@PathVariable("type") ChannelCode type,
            @CurrentMemberId long memberId) {
        log.info("delete /channels/{}", type);
        channelService.deleteChannel(type, memberId);

        return ResponseEntity.noContent().build();
    }
}
