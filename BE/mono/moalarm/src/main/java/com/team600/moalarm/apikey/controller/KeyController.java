package com.team600.moalarm.apikey.controller;

import com.team600.moalarm.apikey.dto.response.MoalarmKeyDto;
import com.team600.moalarm.apikey.service.MoalarmKeyService;
import java.net.URI;
import javax.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/key")
public class KeyController {

    private final MoalarmKeyService moalarmKeyService;

    @GetMapping
    public ResponseEntity<MoalarmKeyDto> getKey(@AuthenticationPrincipal UserDetails userDetails) {
        log.info("GET /key, {}", userDetails.getUsername());
        String memberId = userDetails.getUsername();
        return ResponseEntity.ok(moalarmKeyService.getApiKey(memberId));
    }

    @PostMapping
    public ResponseEntity<MoalarmKeyDto> refreshKey(
            @AuthenticationPrincipal UserDetails userDetails, HttpServletRequest request) {
        log.info("POST /key, {}", userDetails.getUsername());
        String memberId = userDetails.getUsername();
        return ResponseEntity.created(URI.create(request.getRequestURL().toString()))
                .body(moalarmKeyService.refreshApiKey(memberId));
    }
}
