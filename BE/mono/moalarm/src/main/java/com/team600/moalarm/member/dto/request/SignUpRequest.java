package com.team600.moalarm.member.dto.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;

@ToString(of = {"email"})
@Getter
@Setter
public class SignUpRequest {

    @Email(message = "이메일 형식이 올바르지 않습니다.")
    private String email;

    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,}$", message = "비밀번호는 최소 8자 이상, 영문, 숫자, 특수문자가 모두 포함되어야 합니다.")
    private String password;

    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,}$", message = "비밀번호는 최소 8자 이상, 영문, 숫자, 특수문자가 모두 포함되어야 합니다.")
    private String confirmPassword;

    public boolean isPasswordMatch() {
        return password.equals(confirmPassword);
    }

}
