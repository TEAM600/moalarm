package com.team600.moalarm.auth.data.repository;

import com.team600.moalarm.auth.data.entity.Member;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

@Slf4j
@RequiredArgsConstructor
@Repository
public class MemmoryMemberRepository implements MemberRepository{

    private final PasswordEncoder passwordEncoder;

    @Override
    public Optional<Member> findByEmail(String email) {
        return Optional.of(
                Member.builder()
                        .email("test")
                        .password(passwordEncoder.encode("1234"))
                        .build());
    }
}
