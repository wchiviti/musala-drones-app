package io.github.hobbstech.exceptions;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Set;
import java.util.stream.Collectors;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class CustomConstraintViolationException extends ConstraintViolationException {

    public CustomConstraintViolationException(Set<? extends ConstraintViolation<?>> constraintViolations) {

        super(toString(constraintViolations), constraintViolations);

    }

    private static String toString(Set<? extends ConstraintViolation<?>> constraintViolations) {
        return constraintViolations.stream()
                .map(cv -> cv == null ? "null" : cv.getMessage())
                .collect(Collectors.joining(", \n"));
    }
}
