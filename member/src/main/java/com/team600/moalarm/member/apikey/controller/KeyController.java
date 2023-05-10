package com.team600.moalarm.member.apikey.controller;

import com.team600.moalarm.member.apikey.response.KeyResponse;
import com.team600.moalarm.member.apikey.service.ApiKeyService;
import com.team600.moalarm.member.common.annotation.CurrentMemberId;
import java.io.Serializable;
import java.net.URI;
import javax.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/key")
public class KeyController<ID extends Serializable> {

    @Autowired
    private final ApiKeyService<ID, ?> apiKeyService;

    @GetMapping
    public ResponseEntity<KeyResponse> getKey(@CurrentMemberId ID memberId) {
        log.info("GET /key, {}", memberId);
        return ResponseEntity.ok(apiKeyService.getApiKey(memberId));
    }

    @PostMapping
    public ResponseEntity<KeyResponse> refreshKey(@CurrentMemberId ID memberId,
            HttpServletRequest request) {
        log.info("POST /key, {}", memberId);
        return ResponseEntity.created(URI.create(request.getRequestURL().toString()))
                .body(apiKeyService.refreshApiKey(memberId));
    }
}
