package com.team600.moalarm.auth.data.dto.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString(of = {"email"})
@Getter
@Setter
public class SignInRequest {

    private String email;
    private String password;
}
