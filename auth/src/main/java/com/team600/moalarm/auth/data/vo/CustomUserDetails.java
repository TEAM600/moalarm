package com.team600.moalarm.auth.data.vo;

import java.util.Collection;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.SpringSecurityCoreVersion;
import org.springframework.security.core.userdetails.UserDetails;

public class CustomUserDetails implements UserDetails {

    private static final long serialVersionUID = SpringSecurityCoreVersion.SERIAL_VERSION_UID;
    @Getter
    private final long memberId;
    private final String username;
    private final Collection<? extends GrantedAuthority> authorities;

    public CustomUserDetails(String username, Collection<? extends GrantedAuthority> authorities) {
        this.memberId = Long.parseLong(username);
        this.username = username;
        this.authorities = authorities;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
