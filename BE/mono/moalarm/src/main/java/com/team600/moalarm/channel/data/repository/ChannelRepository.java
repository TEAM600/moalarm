package com.team600.moalarm.channel.data.repository;

import com.team600.moalarm.channel.data.entity.Channel;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ChannelRepository extends JpaRepository<Channel, Long> {

    @Query("select c from Channel c where c.delYn = 'N' and c.memberId=:memberId")
    List<Channel> findAllByMemberId(@Param("memberId") long memberId);
}
