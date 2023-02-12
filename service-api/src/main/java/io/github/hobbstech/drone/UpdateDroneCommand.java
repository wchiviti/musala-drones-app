package io.github.hobbstech.drone;

import io.github.hobbstech.enums.DroneState;
import jakarta.validation.constraints.NotNull;

public record UpdateDroneCommand(
        @NotNull(message = "UUID should be provided")
        String uuid,
        @NotNull(message = "Drone state should be provided") DroneState state,
        double batteryCapacity) {
}
