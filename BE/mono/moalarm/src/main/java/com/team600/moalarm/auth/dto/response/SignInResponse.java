package com.team600.moalarm.auth.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
@AllArgsConstructor
public class SignInResponse {

    private String token;
}
