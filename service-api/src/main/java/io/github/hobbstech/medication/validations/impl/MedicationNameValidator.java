package io.github.hobbstech.medication.validations.impl;

import io.github.hobbstech.medication.validations.api.ValidMedicationName;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import static java.util.Objects.isNull;

public class MedicationNameValidator implements ConstraintValidator<ValidMedicationName, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {

        if (isNull(value)) {
            return true;
        }

        if (value.isBlank()) {
            return false;
        }

        return value.chars()
                .allMatch(ch -> Character.isDigit(ch) || Character.isLetter(ch) || '-' == ch || '_' == ch);

    }
}
