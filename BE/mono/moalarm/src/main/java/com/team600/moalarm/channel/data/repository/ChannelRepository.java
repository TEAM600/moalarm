package com.team600.moalarm.channel.data.repository;

import com.team600.moalarm.channel.data.code.ChannelCode;
import com.team600.moalarm.channel.data.entity.Channel;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ChannelRepository extends JpaRepository<Channel, Long> {

    @Query("SELECT c FROM Channel c WHERE c.memberId=:memberId AND c.delYn = 'N'")
    List<Channel> findAllByMemberId(long memberId);

    @Query("SELECT c FROM Channel c WHERE c.memberId=:memberId AND c.type = :type AND c.delYn = 'N'")
    Channel findAllByMemberIdAndType(@Param("type") ChannelCode channelCode, long memberId);

    @Query("SELECT COUNT(*) > 0 "
            + "FROM Channel c "
            + "WHERE c.memberId = :memberId "
            + "AND c.type = :type "
            + "AND c.delYn = 'N'")
    boolean existsByMemberIdAndType(long memberId, @Param("type") ChannelCode channelCode);

}
