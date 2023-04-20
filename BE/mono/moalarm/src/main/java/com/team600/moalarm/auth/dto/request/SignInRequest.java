package com.team600.moalarm.auth.dto.request;

import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class SignInRequest {

    private String email;
    private String password;
}
