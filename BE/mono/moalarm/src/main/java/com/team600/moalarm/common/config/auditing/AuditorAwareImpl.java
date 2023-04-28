package com.team600.moalarm.common.config.auditing;

import com.team600.moalarm.auth.vo.CustomUserDetails;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

@Slf4j
public class AuditorAwareImpl implements AuditorAware<Long> {

    @Override
    public Optional<Long> getCurrentAuditor() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()
                || authentication instanceof AnonymousAuthenticationToken) {
            log.debug("Not found AuthenticationToken");
            return Optional.empty();
        }

        CustomUserDetails user = (CustomUserDetails) authentication.getPrincipal();
        return Optional.of(user.getMemberId());
    }

}