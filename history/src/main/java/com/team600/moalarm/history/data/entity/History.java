package com.team600.moalarm.history.data.entity;

import com.team600.moalarm.history.common.code.ChannelCode;
import com.team600.moalarm.history.common.converter.EncryptStringColumnConverter;
import com.team600.moalarm.history.common.entity.BaseEntity;
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

    @Column(nullable = false)
    private long alarmRequestId;

    @Enumerated(EnumType.ORDINAL)
    @Column(nullable = false)
    private ChannelCode type;

    @Convert(converter = EncryptStringColumnConverter.class)
    @Column(nullable = false)
    private String receiver;

    @Column(nullable = false)
    private String success;

    @Builder
    public History(long memberId, long alarmRequestId, ChannelCode type, String receiver, String success) {
        this.memberId = memberId;
        this.alarmRequestId = alarmRequestId;
        this.type = type;
        this.receiver = receiver;
        this.success = success;
    }
}
