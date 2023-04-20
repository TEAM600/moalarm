package com.team600.moalarm.auth.dto.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
public class SignIn {

    private String email;
    private String password;
}
