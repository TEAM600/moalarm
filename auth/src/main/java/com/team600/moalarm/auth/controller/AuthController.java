package com.team600.moalarm.auth.controller;

import com.team600.moalarm.auth.data.dto.request.SignInRequest;
import com.team600.moalarm.auth.data.dto.response.SignInResponse;
import com.team600.moalarm.auth.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
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

        SignInResponse signInResponse = authService.signIn(signInRequest);
        return ResponseEntity.ok(signInResponse);
    }
}

