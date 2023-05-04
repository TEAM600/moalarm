package com.team600.moalarm.history.repository;

import com.team600.moalarm.history.dto.response.HistoryChartDataDto;
import com.team600.moalarm.history.entity.History;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface HistoryRepository extends JpaRepository<History, Long> {

    @Query("SELECT h FROM History h WHERE h.memberId = :memberId AND h.delYn = 'N' ORDER BY h.createdAt DESC")
    List<History> findAllByMemberId(long memberId);

    @Query("SELECT new com.team600.moalarm.history.dto.response.HistoryChartDataDto(h.type, DATE(h.createdAt), COUNT(h.memberId))\n"
            + "FROM History h\n"
            + "WHERE h.memberId = :memberId\n"
            + "AND h.delYn = 'N'\n"
            + "AND DATE(h.createdAt) BETWEEN DATE(:start) AND DATE(:end)\n"
            + "GROUP BY h.type, DATE(h.createdAt)\n"
            + "ORDER BY h.type, DATE(h.createdAt)")
    List<HistoryChartDataDto> getHistoryChartDateset(long memberId, LocalDate start,
            LocalDate end);
}
