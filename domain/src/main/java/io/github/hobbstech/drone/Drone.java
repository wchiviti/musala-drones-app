package io.github.hobbstech.drone;

import io.github.hobbstech.enums.DroneModel;
import io.github.hobbstech.enums.DroneState;
import io.github.hobbstech.utils.DefaultIdentifierAuditedEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "drones",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "uuid", name = "drones_uuid"),
                @UniqueConstraint(columnNames = "serial_number", name = "drones_serial_number")
        },
        indexes = {@Index(name = "drones_uuid_idx", columnList = "uuid")})
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
class Drone extends DefaultIdentifierAuditedEntity {

    @Column(name = "serial_number", length = 100)
    private String serialNumber;

    @Column(name = "model")
    @Enumerated(EnumType.STRING)
    private DroneModel model;

    @Column(name = "weight_limit")
    private int weightLimit;

    @Column(name = "battery_capacity")
    private double batteryCapacity;

    @Column(name = "state")
    @Enumerated(EnumType.STRING)
    private DroneState state;

}
