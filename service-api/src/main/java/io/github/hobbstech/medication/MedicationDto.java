package io.github.hobbstech.medication;

import com.fasterxml.jackson.annotation.JsonIgnore;

public record MedicationDto(
        String uuid,
        @JsonIgnore
        long id,
        String name,
        String code,
        String imageFileName,
        double weight,
        String droneUuid) {
}
