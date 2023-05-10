package com.team600.moalarm.member.channel.data.repository;

import com.team600.moalarm.member.channel.data.code.ChannelCode;
import com.team600.moalarm.member.channel.data.entity.Channel;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ChannelRepository extends JpaRepository<Channel, Long> {

    @Query("SELECT c FROM Channel c WHERE c.memberId=:memberId AND c.delYn = 'N'")
    List<Channel> findAllByMemberId(long memberId);

    @Query("SELECT c FROM Channel c WHERE c.memberId=:memberId AND c.type = :type AND c.delYn = 'N'")
    Optional<Channel> findByMemberIdAndType(long memberId, @Param("type") ChannelCode channelCode);

    @Query("SELECT COUNT(*) > 0 "
            + "FROM Channel c "
            + "WHERE c.memberId = :memberId "
            + "AND c.type = :type "
            + "AND c.delYn = 'N'")
    boolean existsByMemberIdAndType(long memberId, @Param("type") ChannelCode channelCode);

}
