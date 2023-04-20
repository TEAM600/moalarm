package com.team600.moalarm.auth.dto.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
public class SignInRequest {

    private String email;
    private String password;
}
