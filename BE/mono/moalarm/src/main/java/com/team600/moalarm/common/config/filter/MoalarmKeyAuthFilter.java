package com.team600.moalarm.common.config.filter;

import com.team600.moalarm.auth.vo.CustomUserDetails;
import com.team600.moalarm.member.data.repository.MemberRepository;
import com.team600.moalarm.member.exception.MoalarmKeyNotFoundException;
import java.io.IOException;
import java.util.Collection;
import java.util.Set;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Slf4j
@Component
public class MoalarmKeyAuthFilter extends OncePerRequestFilter {

    private static final String AUTHORIZATION_TYPE = "MoalarmKey ";
    public static final String ROLE_API = "API";
    private final Collection<? extends GrantedAuthority> authorities;
    private final MemberRepository memberRepository;

    public MoalarmKeyAuthFilter(
            MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
        this.authorities = Set.of(new SimpleGrantedAuthority(ROLE_API));
    }

    @Override
    protected void doFilterInternal(@NotNull HttpServletRequest request,
            @NotNull HttpServletResponse response,
            @NotNull FilterChain filterChain) throws ServletException, IOException {

        String key = AuthFilterUtils.resolveAuthHeader(request, AUTHORIZATION_TYPE);
        log.info("MoalarmKeyAuthFilter : {}", key);

        if (key != null) {
            long memberId = memberRepository.findByMoalarmKey(key)
                    .orElseThrow(MoalarmKeyNotFoundException::new)
                    .getId();
            CustomUserDetails principal = new CustomUserDetails(Long.toString(memberId),
                    authorities);
            Authentication authentication = new UsernamePasswordAuthenticationToken(principal,
                    key, authorities);

            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        filterChain.doFilter(request, response);
    }
}
