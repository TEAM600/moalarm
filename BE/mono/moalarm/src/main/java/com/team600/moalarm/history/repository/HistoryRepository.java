package com.team600.moalarm.history.repository;

import com.team600.moalarm.history.entity.History;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface HistoryRepository extends JpaRepository<History, Long> {

    @Query("SELECT h FROM History h WHERE h.memberId = :memberId AND h.delYn = 'N'")
    List<History> findAllByMemberId(long memberId);

}
