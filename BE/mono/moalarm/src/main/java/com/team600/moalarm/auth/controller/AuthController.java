package com.team600.moalarm.auth.controller;

import com.team600.moalarm.auth.dto.request.Login;
import com.team600.moalarm.auth.dto.response.Token;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/auth")
@RestController
public class AuthController {

    @PostMapping("/login")
    public ResponseEntity<Token> signin(@RequestBody Login login)
            throws AuthenticationException {
        Token token = authService.signin(login);
        return ResponseEntity.ok(token);
    }
}

