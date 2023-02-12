package io.github.hobbstech.medication.validations.api;

import io.github.hobbstech.medication.validations.impl.MedicationNameValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE,
        ElementType.CONSTRUCTOR, ElementType.PARAMETER, ElementType.TYPE_USE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Repeatable(ValidMedicationName.List.class)
@Constraint(validatedBy = MedicationNameValidator.class)
public @interface ValidMedicationName {

    String message() default "Allowed only letters, numbers, ‘-‘, ‘_’";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    @Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE,
            ElementType.CONSTRUCTOR, ElementType.PARAMETER, ElementType.TYPE_USE})
    @Retention(RetentionPolicy.RUNTIME)
    @Documented
    @interface List {

        ValidMedicationName[] value();

    }

}
