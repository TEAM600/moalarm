package com.team600.moalarm.member.entity;

import com.team600.moalarm.common.BaseEntity;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Member extends BaseEntity {

    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private String password;
    private String moalarmKey;
    private LocalDateTime moalarmKeyRefreshedAt;
    private int channelRegistrationStatus;

    @Builder
    public Member(String email, String password, String moalarmKey,
            LocalDateTime moalarmKeyRefreshedAt, int channelRegistrationStatus) {
        this.email = email;
        this.password = password;
        this.moalarmKey = moalarmKey;
        this.moalarmKeyRefreshedAt = moalarmKeyRefreshedAt;
        this.channelRegistrationStatus = channelRegistrationStatus;
    }

    public void refreshMoalarmKey(String moalarmKey) {
        this.moalarmKey = moalarmKey;
        this.moalarmKeyRefreshedAt = LocalDateTime.now();
    }

    public void deleteChannel(int channelCode) {
        this.channelRegistrationStatus &= ~(1 << channelCode);
    }

    public void registChannel(int channelCode) {
        this.channelRegistrationStatus |= 1 << channelCode;
    }
}
