package com.team600.moalarm.member.entity;

import com.team600.moalarm.common.BaseEntity;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Member extends BaseEntity {

    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private String password;
    private String moalarmKey;
    private LocalDateTime moalarmKeyRefreshedAt;
    private int channelRegistrationStatus;

    public void refreshMoalarmKey(String moalarmKey) {
        this.moalarmKey = moalarmKey;
        this.moalarmKeyRefreshedAt = LocalDateTime.now();
    }
}