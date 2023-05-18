package com.team600.moalarm.history.data.entity;

import com.team600.moalarm.history.common.entity.BaseEntity;
import javax.persistence.Column;
import javax.persistence.Entity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class AlarmRequest extends BaseEntity {
    @Column(nullable = false)
    private long memberId;

    @Column(nullable = false)
    private int alarmCnt;

    @Column(nullable = false)
    private String doneYn;

    public void setDone() {
        this.doneYn = "Y";
    }

    @Builder
    public AlarmRequest(long memberId, int alarmCnt, String doneYn) {
        this.memberId = memberId;
        this.alarmCnt = alarmCnt;
        this.doneYn = doneYn;
    }
}
