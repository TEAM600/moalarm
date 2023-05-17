package com.team600.moalarm.history.data.repository;

import com.team600.moalarm.history.data.entity.AlarmRequest;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AlarmRequestRepository extends JpaRepository<AlarmRequest, Long> {

    @Query("SELECT ar FROM AlarmRequest ar WHERE ar.memberId = :memberId AND ar.delYn = 'N' ORDER BY ar.createdAt DESC")
    List<AlarmRequest> findAllByMemberId(long memberId);
}
