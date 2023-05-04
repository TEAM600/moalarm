package com.team600.moalarm.member.data.repository;

import com.team600.moalarm.member.data.document.Member;
import java.util.Optional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MemberMongoRepository extends MongoRepository<Member, String> {

    @Query(value = "{'email': ?0,'delYN': 'N'}")
    boolean existsByEmail(String email);

    @Query("SELECT COUNT(*) > 0 FROM Member m WHERE m.moalarmKey = :moalarmKey AND m.delYn = 'N'")
    boolean existsByMoalarmKey(String moalarmKey);

    @Query("SELECT m FROM Member m WHERE m.moalarmKey = :moalarmKey AND m.delYn = 'N'")
    Optional<com.team600.moalarm.member.data.entity.Member> findByMoalarmKey(String moalarmKey);

    @Query("SELECT m FROM Member m WHERE m.email = :email AND m.delYn = 'N'")
    Optional<com.team600.moalarm.member.data.entity.Member> findByEmail(String email);

}
