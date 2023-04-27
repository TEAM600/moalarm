package com.team600.moalarm.common.config.filter;

import com.team600.moalarm.auth.vo.CustomUserDetails;
import com.team600.moalarm.member.exception.MoalarmKeyNotFoundException;
import com.team600.moalarm.member.repository.MemberRepository;
import java.io.IOException;
import java.util.Collection;
import java.util.Set;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@RequiredArgsConstructor
@Slf4j
@Component
public class MoalarmKeyFilter extends OncePerRequestFilter {

    private static final String AUTHORIZATION_HEADER = "Authorization";
    private static final String AUTHORIZATION_TYPE = "MoalarmKey ";
    public static final String ROLE_API = "API";
    private final MemberRepository memberRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
            FilterChain filterChain) throws ServletException, IOException {

        String key = resolveAuthKey(request);

        log.info("Moalarm filter : {}", key);

        if (key != null) {
            long memberId = memberRepository.findByMoalarmKey(key)
                    .orElseThrow(MoalarmKeyNotFoundException::new)
                    .getId();
            Collection<? extends GrantedAuthority> authorities = getAuthorities();
            CustomUserDetails principal = new CustomUserDetails(Long.toString(memberId),
                    authorities);
            Authentication authentication = new UsernamePasswordAuthenticationToken(principal,
                    key, authorities);

            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        filterChain.doFilter(request, response);
    }

    private Collection<? extends GrantedAuthority> getAuthorities() {
        return Set.of(new SimpleGrantedAuthority(ROLE_API));
    }

    private String resolveAuthKey(HttpServletRequest request) {
        String authorization = request.getHeader(AUTHORIZATION_HEADER);
        String key = null;
        if (authorization != null && authorization.startsWith(AUTHORIZATION_TYPE)) {
            key = authorization.replaceFirst("^" + AUTHORIZATION_TYPE, "");
        }
        return key;
    }
}
