package com.team600.moalarm.member.channel.data.entity;

import com.team600.moalarm.member.channel.data.code.ChannelCode;
import com.team600.moalarm.member.common.BaseEntity;
import com.team600.moalarm.member.common.utils.EncryptStringColumnConverter;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Channel extends BaseEntity {

    @Column(nullable = false)
    private long memberId;
    @Column(nullable = false)
    @Enumerated(EnumType.ORDINAL)
    private ChannelCode type;
    @Convert(converter = EncryptStringColumnConverter.class)
    @Column(nullable = false)
    private String apiKey;
    @Convert(converter = EncryptStringColumnConverter.class)
    @Column(nullable = false)
    private String secret;
    @Convert(converter = EncryptStringColumnConverter.class)
    @Column
    private String extraValue;
}
