package com.team600.moalarm.auth.data.repository;

import com.team600.moalarm.auth.data.entity.Member;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;


public interface MemberRepository {

    Optional<Member> findByEmail(String email);
}
