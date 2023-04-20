package com.team600.moalarm.auth.controller;

import com.team600.moalarm.auth.dto.request.SignInRequest;
import com.team600.moalarm.auth.dto.response.SignInResponse;
import com.team600.moalarm.auth.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
public class AuthController {

    private final AuthService authService;

    @PostMapping("/auth/signin")
    public ResponseEntity<SignInResponse> signIn(@RequestBody SignInRequest signInRequest)
            throws AuthenticationException {
        log.info("signin : {}", signInRequest);

        SignInResponse signInResponse = authService.signIn(signInRequest);
        return ResponseEntity.ok(signInResponse);
    }

    // TODO: 2023-04-20 handler 구현 필요
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> handleRuntime(RuntimeException e) {
        return ResponseEntity.ok(e.getMessage());
    }
}

