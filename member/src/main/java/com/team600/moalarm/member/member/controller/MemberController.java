package com.team600.moalarm.member.member.controller;

import com.team600.moalarm.member.common.annotation.CurrentMemberId;
import com.team600.moalarm.member.member.request.SignUpRequest;
import com.team600.moalarm.member.member.service.MemberService;
import java.io.Serializable;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/member")
public class MemberController<ID extends Serializable> {

    private final MemberService<ID> memberService;

    @PostMapping
    public ResponseEntity<Void> signUp(@Validated @RequestBody final SignUpRequest signUpRequest) {
        log.info("POST /member");
        memberService.signUp(signUpRequest);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping
    public ResponseEntity<Void> withdrawal(@CurrentMemberId ID memberId) {
        log.info("DELETE /member {}", memberId);
        memberService.withdrawal(memberId);
        return ResponseEntity.noContent().build();
    }

}
