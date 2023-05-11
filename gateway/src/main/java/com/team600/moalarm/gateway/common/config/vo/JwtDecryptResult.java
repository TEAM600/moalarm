package com.team600.moalarm.gateway.common.config.vo;

import java.util.Collection;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

@AllArgsConstructor
@Getter
public class JwtDecryptResult {

    private final String subject;
    private final Collection<SimpleGrantedAuthority> authorities;
}
