package io.github.hobbstech.utils;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Getter
public class AuditableEntity extends BaseEntity {

    @CreatedBy
    @Column
    @JsonIgnore
    private String createdBy;

    @LastModifiedBy
    @Column
    @JsonIgnore
    private String lastModifiedBy;

    @UpdateTimestamp
    @Column
    @JsonIgnore
    private LocalDateTime lastModifiedDate;

    @CreationTimestamp
    @Column
    @JsonIgnore
    private LocalDateTime createdDate;

}
