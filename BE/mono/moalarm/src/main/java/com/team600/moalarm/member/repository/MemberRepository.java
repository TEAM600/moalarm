package com.team600.moalarm.member.repository;

import com.team600.moalarm.member.entiry.Member;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class MemberRepository {

    private final Map<String, Member> storage = new ConcurrentHashMap<>();

    public MemberRepository() {
        String email = "test";
        storage.put(email, new Member(email, "1234"));
    }

    public Optional<Member> findByEmail(String email) {
        return Optional.ofNullable(storage.get(email));
    }

}
