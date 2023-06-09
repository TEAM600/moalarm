package com.team600.moalarm.member.member.repository;

import com.team600.moalarm.member.member.entity.Member;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository<ID> extends JpaRepository<Member, ID> {

    @Query("SELECT COUNT(*) > 0 FROM Member m WHERE m.email = :email AND m.delYn = 'N'")
    boolean existsByEmail(String email);

    @Query("SELECT COUNT(*) > 0 FROM Member m WHERE m.moalarmKey = :moalarmKey AND m.delYn = 'N'")
    boolean existsByMoalarmKey(String moalarmKey);

    @Query("SELECT m FROM Member m WHERE m.moalarmKey = :moalarmKey AND m.delYn = 'N'")
    Optional<Member> findByMoalarmKey(String moalarmKey);

    @Query("SELECT m FROM Member m WHERE m.email = :email AND m.delYn = 'N'")
    Optional<Member> findByEmail(String email);

}
