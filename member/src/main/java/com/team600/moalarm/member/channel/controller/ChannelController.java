package com.team600.moalarm.member.channel.controller;

import com.team600.moalarm.member.channel.data.code.ChannelCode;
import com.team600.moalarm.member.channel.data.dto.request.ChannelCreateRequest;
import com.team600.moalarm.member.channel.data.dto.response.ChannelRegistrationResponse;
import com.team600.moalarm.member.channel.data.dto.response.ChannelsSecretResponse;
import com.team600.moalarm.member.channel.service.ChannelSaveService;
import com.team600.moalarm.member.channel.service.ChannelService;
import com.team600.moalarm.member.common.annotation.CurrentMemberId;
import com.team600.moalarm.member.common.utils.AuthUtils;
import com.team600.moalarm.member.member.service.MemberService;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
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
public class ChannelController<ID extends Serializable> {

    private final MemberService<ID> memberService;
    private final ChannelService channelService;
    private final Map<String, ChannelSaveService> channelSaveService;

    @GetMapping
    public ResponseEntity<List<ChannelRegistrationResponse>> getChannels(
            @CurrentMemberId ID memberId) {
        log.info("GET /channels");
        return ResponseEntity.ok(memberService.getChannels(memberId));
    }

    @GetMapping("/secret")
    public ResponseEntity<ChannelsSecretResponse<?>> getChannelsSecret(HttpServletRequest request) {
        String moalarmKey = AuthUtils.resolveAuthorizationHeader(request);
        log.info("GET /channels/secret - MoalarmKey: {}", moalarmKey);
        return ResponseEntity.ok(channelService.getChannelsSecret(moalarmKey));
    }

    @PostMapping("/{type}")
    public ResponseEntity<Void> createChannel(@PathVariable("type") ChannelCode type,
            @RequestBody ChannelCreateRequest requestDto,
            @CurrentMemberId Long memberId) {
        log.info("POST /channels/{}", type);
        Optional.ofNullable(channelSaveService.get(type.getValue() + "ChannelService"))
                .orElseThrow()  //TODO: 23.05.11 유효하지 않은 채널 exception
                .saveChannel(requestDto, memberId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{type}")
    public ResponseEntity<Void> deleteChannel(@PathVariable("type") ChannelCode type,
            @CurrentMemberId Long memberId) {
        log.info("delete /channels/{}", type);
        channelService.deleteChannel(type, memberId);

        return ResponseEntity.noContent().build();
    }
}
