package io.github.hobbstech.drone;

import io.github.hobbstech.enums.DroneModel;
import io.github.hobbstech.enums.DroneState;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record DroneCommand(

        @NotBlank(message = "Drone Serial Number should be provided")
        @Size(max = 100, message = "Serial number should not exceed 100 characters")
        String serialNumber,

        @NotNull(message = "Drone model should be provided")
        DroneModel model,

        @Max(value = 500, message = "Weight limit should not exceed 500")
        int weightLimit,

        double batteryCapacity,

        DroneState state) {
}
