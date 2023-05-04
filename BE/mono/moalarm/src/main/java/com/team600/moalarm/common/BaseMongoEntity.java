package com.team600.moalarm.common;

import java.time.LocalDateTime;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseMongoEntity {

    @Id
    private String id;
    @CreatedDate
    private LocalDateTime createdAt;
    @CreatedBy
    private long createdBy;
    @LastModifiedDate
    private LocalDateTime lastModifiedAt;
    @LastModifiedBy
    private long lastModifiedBy;
    private String delYn;

    @PrePersist
    public void setDefaultValues() {
        this.delYn = this.delYn == null ? "N" : this.delYn;
    }

    public void remove() {
        this.delYn = "Y";
    }
}

