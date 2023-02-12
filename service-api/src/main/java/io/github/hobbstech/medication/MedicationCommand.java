package io.github.hobbstech.medication;

import io.github.hobbstech.medication.validations.api.ValidMedicationCode;
import io.github.hobbstech.medication.validations.api.ValidMedicationName;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record MedicationCommand(

        @ValidMedicationName
        @NotBlank(message = "Medication name should be provided")
        String name,
        @ValidMedicationCode
        @NotNull(message = "Medication code should be provided")
        String code,
        @NotBlank(message = "Picture of the medication case should be provided")
        String imageFileName,
        double weight) {
}
