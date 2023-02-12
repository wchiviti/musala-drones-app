package io.github.hobbstech.utils;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Version;
import lombok.Getter;
import org.hibernate.annotations.NaturalId;

import java.io.Serializable;
import java.util.UUID;

@MappedSuperclass
@Getter
public class BaseEntity implements Serializable {

    @NaturalId
    @Column(nullable = false, updatable = false, unique = true)
    private String uuid;

    @Version
    @JsonIgnore
    private Integer version;

    @PrePersist
    void setupUuid() {
        this.uuid = UUID.randomUUID().toString();
    }

}
