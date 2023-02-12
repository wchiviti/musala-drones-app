package io.github.hobbstech.medication.validations.impl;

import io.github.hobbstech.medication.validations.api.ValidMedicationCode;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import static java.util.Objects.isNull;

public class MedicationCodeValidator implements ConstraintValidator<ValidMedicationCode, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {

        if (isNull(value)) {
            return true;
        }

        if (value.isBlank()) {
            return false;
        }

        return value.chars()
                .allMatch(ch -> Character.isDigit(ch) || Character.isLetter(ch) || '_' == ch);

    }
}
