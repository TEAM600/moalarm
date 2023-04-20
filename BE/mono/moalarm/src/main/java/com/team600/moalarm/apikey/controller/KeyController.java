package com.team600.moalarm.apikey.controller;

import com.team600.moalarm.apikey.dto.response.ApiKeyDto;
import com.team600.moalarm.apikey.service.ApiKeyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/key")
public class KeyController {

    private final ApiKeyService apiKeyService;

    @GetMapping
    public ResponseEntity<ApiKeyDto> getKey() {
        log.info("GET /key");
        String memberId = null; //TODO
        return ResponseEntity.ok(apiKeyService.getApiKey(memberId));
    }

    @PutMapping
    public ResponseEntity<ApiKeyDto> updateKey() {
        log.info("PUT /key");
        String memberId = null; //TODO
        return ResponseEntity.ok(apiKeyService.updateApiKey(memberId));
    }
}
