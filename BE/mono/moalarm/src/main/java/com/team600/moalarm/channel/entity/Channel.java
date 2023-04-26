package com.team600.moalarm.channel.entity;

import com.team600.moalarm.common.BaseEntity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
    private int type;
    @Column(nullable = false)
    private String apiKey;
    @Column(nullable = false)
    private String secret;
    @Column
    private String phone;
}
