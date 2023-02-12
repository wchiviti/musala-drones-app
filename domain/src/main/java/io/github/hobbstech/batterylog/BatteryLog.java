
package io.github.hobbstech.batterylog;

import io.github.hobbstech.utils.DefaultIdentifierAuditedEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Table(name = "battery_log",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "uuid", name = "battery_log_uuid"),
        })
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
class BatteryLog extends DefaultIdentifierAuditedEntity {

    @Column(name = "battery_capacity")
    private double batteryCapacity;

    @Column(name = "drone_uuid", nullable = false)
    private String droneUuid;

    @Column(name = "log_time")
    @CreationTimestamp
    private LocalDateTime logTime;

}
