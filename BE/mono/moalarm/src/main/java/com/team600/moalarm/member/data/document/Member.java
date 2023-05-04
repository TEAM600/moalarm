package com.team600.moalarm.member.data.document;

import com.team600.moalarm.channel.data.entity.Channel;
import com.team600.moalarm.common.BaseMongoEntity;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Getter;

@Getter
public class Member extends BaseMongoEntity {

    private String email;
    private String password;
    private String moalarmKey;
    private LocalDateTime moalarmKeyRefreshedAt;
    private List<Channel> channels;
}
