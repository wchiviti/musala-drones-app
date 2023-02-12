package io.github.hobbstech.drone;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.github.hobbstech.enums.DroneModel;
import io.github.hobbstech.enums.DroneState;

public record DroneDto(String uuid,
                       @JsonIgnore
                       long id,
                       String serialNumber,
                       DroneModel model,
                       int weightLimit,
                       double batteryPercentage,
                       DroneState state) {
}
