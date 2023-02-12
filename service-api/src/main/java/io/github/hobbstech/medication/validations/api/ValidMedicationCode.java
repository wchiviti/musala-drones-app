package io.github.hobbstech.medication.validations.api;

import io.github.hobbstech.medication.validations.impl.MedicationCodeValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE,
        ElementType.CONSTRUCTOR, ElementType.PARAMETER, ElementType.TYPE_USE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Repeatable(ValidMedicationCode.List.class)
@Constraint(validatedBy = MedicationCodeValidator.class)
public @interface ValidMedicationCode {

    String message() default "Allowed only upper case letters, underscore and numbers";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    @Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE,
            ElementType.CONSTRUCTOR, ElementType.PARAMETER, ElementType.TYPE_USE})
    @Retention(RetentionPolicy.RUNTIME)
    @Documented
    @interface List {

        ValidMedicationCode[] value();

    }

}
