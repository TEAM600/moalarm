package com.team600.moalarm.history.entity;

import com.team600.moalarm.channel.data.code.ChannelCode;
import com.team600.moalarm.common.BaseEntity;
import com.team600.moalarm.common.component.EncryptStringColumnConverter;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class History extends BaseEntity {

    @Column(nullable = false)
    private long memberId;

    @Enumerated(EnumType.ORDINAL)
    @Column(nullable = false)
    private ChannelCode type;

    @Convert(converter = EncryptStringColumnConverter.class)
    @Column(nullable = false)
    private String receiver;

    @Column(nullable = false)
    private String success;

    @Builder
    public History(long memberId, ChannelCode type, String receiver, String success) {
        this.memberId = memberId;
        this.type = type;
        this.receiver = receiver;
        this.success = success;
    }
}
