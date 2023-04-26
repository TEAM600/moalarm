package com.team600.moalarm.member.controller;

import com.team600.moalarm.member.dto.request.SignUpRequest;
import com.team600.moalarm.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/member")
public class MemberController {

    private final MemberService memberService;

    @PostMapping
    public ResponseEntity<Void> signUp(@Validated @RequestBody final SignUpRequest signUpRequest) {
        log.info("POST /member");
        memberService.signUp(signUpRequest);
        return ResponseEntity.ok().build();
    }

}
