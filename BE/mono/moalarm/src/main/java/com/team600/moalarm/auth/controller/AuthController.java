package com.team600.moalarm.auth.controller;

import com.team600.moalarm.auth.dto.request.SignIn;
import com.team600.moalarm.auth.dto.response.Token;
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
    public ResponseEntity<Token> signIn(@RequestBody SignIn signIn)
            throws AuthenticationException {
        log.info("signin : {}", signIn);

        Token token = authService.signIn(signIn);
        return ResponseEntity.ok(token);
    }

    // TODO: 2023-04-20 handler 구현 필요
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> handleRuntime(RuntimeException e) {
        return ResponseEntity.ok(e.getMessage());
    }
}

