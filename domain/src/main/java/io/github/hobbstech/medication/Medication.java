package io.github.hobbstech.medication;

import io.github.hobbstech.utils.DefaultIdentifierAuditedEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "medications",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "uuid", name = "medications_uuid"),
        },
        indexes = {@Index(name = "drones_uuid_idx", columnList = "uuid")})
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
class Medication extends DefaultIdentifierAuditedEntity {

    @Column(name = "name")
    private String name;

    @Column(name = "code")
    private String code;

    @Column(name = "image_file_name")
    private String imageFileName;

    @Column(name = "weight")
    private double weight;

}
