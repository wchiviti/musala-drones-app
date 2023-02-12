package io.github.hobbstech.exceptions;

import jakarta.validation.ConstraintViolationException;
import jakarta.validation.ValidationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.Objects.nonNull;
import static java.util.stream.Collectors.toSet;

@ControllerAdvice
@Slf4j
public class GeneralExceptionHandler extends ResponseEntityExceptionHandler {

    public static final String ACCESS_DENIED = "Access denied!";
    public static final String INVALID_REQUEST = "Invalid request";
    public static final String ERROR_MESSAGE_TEMPLATE = "message: %s %n requested uri: %s";
    public static final String LIST_JOIN_DELIMITER = ",";
    public static final String FIELD_ERROR_SEPARATOR = ": ";
    public static final String ERRORS_FOR_PATH = "errors {} for path {}";
    public static final String PATH = "path";
    public static final String ERRORS = "error";
    public static final String STATUS = "status";
    public static final String MESSAGE = "message";
    public static final String TIMESTAMP = "timestamp";
    public static final String TYPE = "type";

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException exception, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        List<String> validationErrors = exception.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> error.getField() + FIELD_ERROR_SEPARATOR + error.getDefaultMessage())
                .collect(Collectors.toList());
        return getExceptionResponseEntity(exception, HttpStatus.BAD_REQUEST, request, validationErrors);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(
            HttpMessageNotReadableException exception, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        return getExceptionResponseEntity(exception, HttpStatus.valueOf(status.value()), request,
                Collections.singletonList(exception.getLocalizedMessage()));
    }

    @ExceptionHandler({ConstraintViolationException.class})
    public ResponseEntity<Object> handleConstraintViolation(
            ConstraintViolationException exception, WebRequest request) {
        final List<String> validationErrors = exception.getConstraintViolations().stream().
                map(violation ->
                        violation.getPropertyPath() + FIELD_ERROR_SEPARATOR + violation.getMessage())
                .collect(Collectors.toList());
        return getExceptionResponseEntity(exception, HttpStatus.BAD_REQUEST, request, validationErrors);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public final ResponseEntity<Object> handleAccessDeniedException(AccessDeniedException ex, WebRequest request) {

        ResponseStatus responseStatus =
                ex.getClass().getAnnotation(ResponseStatus.class);
        final HttpStatus status =
                responseStatus != null ? responseStatus.value() : HttpStatus.FORBIDDEN;
        final String localizedMessage = ex.getLocalizedMessage();
        final String path = request.getDescription(false);
        String message = !localizedMessage.isEmpty() ? localizedMessage : status.getReasonPhrase();
        logger.error(String.format(ERROR_MESSAGE_TEMPLATE, message, path));
        logger.debug(String.format(ERROR_MESSAGE_TEMPLATE, message, path), ex);
        return getExceptionResponseEntity(ex, status, request, Collections.singletonList(message));

    }

    @ExceptionHandler(BadCredentialsException.class)
    public final ResponseEntity<Object> handleBadCredentialsException(BadCredentialsException ex, WebRequest request) {
        ResponseStatus responseStatus =
                ex.getClass().getAnnotation(ResponseStatus.class);
        final HttpStatus status =
                responseStatus != null ? responseStatus.value() : HttpStatus.UNAUTHORIZED;
        final String localizedMessage = ex.getLocalizedMessage();
        final String path = request.getDescription(false);
        String message = !localizedMessage.isEmpty() ? localizedMessage : status.getReasonPhrase();
        logger.error(String.format(ERROR_MESSAGE_TEMPLATE, message, path));
        logger.debug(String.format(ERROR_MESSAGE_TEMPLATE, message, path), ex);
        return getExceptionResponseEntity(ex, status, request, Collections.singletonList(message));

    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public final ResponseEntity<Object> handleUsernameNotFoundException(UsernameNotFoundException ex, WebRequest request) {
        ResponseStatus responseStatus =
                ex.getClass().getAnnotation(ResponseStatus.class);
        final HttpStatus status =
                responseStatus != null ? responseStatus.value() : HttpStatus.UNAUTHORIZED;
        final String localizedMessage = ex.getLocalizedMessage();
        final String path = request.getDescription(false);
        String message = !localizedMessage.isEmpty() ? localizedMessage : status.getReasonPhrase();
        logger.error(String.format(ERROR_MESSAGE_TEMPLATE, message, path));
        logger.debug(String.format(ERROR_MESSAGE_TEMPLATE, message, path), ex);
        return getExceptionResponseEntity(ex, status, request, Collections.singletonList(message));
    }

    @ExceptionHandler(InvalidRequestException.class)
    public final ResponseEntity<Object> handleInvalidRequestException(InvalidRequestException ex, WebRequest request) {

        ResponseStatus responseStatus =
                ex.getClass().getAnnotation(ResponseStatus.class);
        final HttpStatus status =
                responseStatus != null ? responseStatus.value() : HttpStatus.INTERNAL_SERVER_ERROR;
        final String localizedMessage = ex.getLocalizedMessage();
        final String path = request.getDescription(false);
        String message = !localizedMessage.isEmpty() ? localizedMessage : status.getReasonPhrase();
        logger.error(String.format(ERROR_MESSAGE_TEMPLATE, message, path));
        logger.debug(String.format(ERROR_MESSAGE_TEMPLATE, message, path), ex);
        return getExceptionResponseEntity(ex, status, request, Collections.singletonList(message));
    }

    @ExceptionHandler(CustomConstraintViolationException.class)
    public final ResponseEntity<Object> handleCustomConstraintViolationException(CustomConstraintViolationException ex, WebRequest request) {
        var message = String.join(", ", ex.getConstraintViolations().stream()
                .map(cv -> cv == null ? "null" : cv.getPropertyPath() + ": " + cv.getMessage())
                .collect(toSet()));
        ResponseStatus responseStatus =
                ex.getClass().getAnnotation(ResponseStatus.class);
        final HttpStatus status =
                responseStatus != null ? responseStatus.value() : HttpStatus.INTERNAL_SERVER_ERROR;
        final String path = request.getDescription(false);
        logger.error(String.format(ERROR_MESSAGE_TEMPLATE, message, path));
        logger.debug(String.format(ERROR_MESSAGE_TEMPLATE, message, path), ex);
        return getExceptionResponseEntity(ex, status, request, Collections.singletonList(message));
    }

    @ExceptionHandler(RecordNotFoundException.class)
    public final ResponseEntity<Object> handleRecordNotFoundException(RecordNotFoundException ex, WebRequest request) {
        ResponseStatus responseStatus =
                ex.getClass().getAnnotation(ResponseStatus.class);
        final HttpStatus status =
                responseStatus != null ? responseStatus.value() : HttpStatus.INTERNAL_SERVER_ERROR;
        final String localizedMessage = ex.getLocalizedMessage();
        final String path = request.getDescription(false);
        String message = !localizedMessage.isEmpty() ? localizedMessage : status.getReasonPhrase();
        logger.error(String.format(ERROR_MESSAGE_TEMPLATE, message, path));
        logger.debug(String.format(ERROR_MESSAGE_TEMPLATE, message, path), ex);
        return getExceptionResponseEntity(ex, status, request, Collections.singletonList(message));
    }

    @ExceptionHandler(NoSuchElementException.class)
    public final ResponseEntity<Object> handleNoSuchElementException(NoSuchElementException ex, WebRequest request) {
        ResponseStatus responseStatus =
                ex.getClass().getAnnotation(ResponseStatus.class);
        final HttpStatus status =
                responseStatus != null ? responseStatus.value() : HttpStatus.NOT_FOUND;
        final String localizedMessage = ex.getLocalizedMessage();
        final String path = request.getDescription(false);
        String message = !localizedMessage.isEmpty() ? localizedMessage : status.getReasonPhrase();
        logger.error(String.format(ERROR_MESSAGE_TEMPLATE, message, path));
        logger.debug(String.format(ERROR_MESSAGE_TEMPLATE, message, path), ex);
        return getExceptionResponseEntity(ex, status, request, Collections.singletonList(message));
    }

    @ExceptionHandler(NullPointerException.class)
    public final ResponseEntity<Object> handleNullPointerException(NullPointerException ex, WebRequest request) {
        ResponseStatus responseStatus =
                ex.getClass().getAnnotation(ResponseStatus.class);
        final HttpStatus status =
                responseStatus != null ? responseStatus.value() : HttpStatus.INTERNAL_SERVER_ERROR;
        final String localizedMessage = ex.getLocalizedMessage();
        final String path = request.getDescription(false);
        String message = !localizedMessage.isEmpty() ? localizedMessage : status.getReasonPhrase();
        logger.error(String.format(ERROR_MESSAGE_TEMPLATE, message, path));
        logger.debug(String.format(ERROR_MESSAGE_TEMPLATE, message, path), ex);
        return getExceptionResponseEntity(ex, status, request, Collections.singletonList(message));
    }

    @ExceptionHandler(ValidationException.class)
    public final ResponseEntity<Object> handleValidationException(ValidationException ex, WebRequest request) {
        ResponseStatus responseStatus =
                ex.getClass().getAnnotation(ResponseStatus.class);
        final HttpStatus status =
                responseStatus != null ? responseStatus.value() : HttpStatus.BAD_REQUEST;
        final String localizedMessage = ex.getLocalizedMessage();
        final String path = request.getDescription(false);
        String message = !localizedMessage.isEmpty() ? localizedMessage : status.getReasonPhrase();
        logger.error(String.format(ERROR_MESSAGE_TEMPLATE, message, path));
        logger.debug(String.format(ERROR_MESSAGE_TEMPLATE, message, path), ex);
        return getExceptionResponseEntity(ex, status, request, Collections.singletonList(message));
    }


    @ExceptionHandler({Exception.class})
    public ResponseEntity<Object> handleAllExceptions(Exception exception, WebRequest request) {
        ResponseStatus responseStatus =
                exception.getClass().getAnnotation(ResponseStatus.class);
        final HttpStatus status =
                responseStatus != null ? responseStatus.value() : HttpStatus.INTERNAL_SERVER_ERROR;
        final String localizedMessage = exception.getLocalizedMessage();
        final String path = request.getDescription(false);
        String message = !localizedMessage.isEmpty() ? localizedMessage : status.getReasonPhrase();
        logger.error(String.format(ERROR_MESSAGE_TEMPLATE, message, path));
        logger.debug(String.format(ERROR_MESSAGE_TEMPLATE, message, path), exception);
        return getExceptionResponseEntity(exception, status, request, Collections.singletonList(message));
    }

    private ResponseEntity<Object> getExceptionResponseEntity(final Exception exception,
                                                              final HttpStatus status,
                                                              final WebRequest request,
                                                              final List<String> errors) {
        final Map<String, Object> body = new LinkedHashMap<>();
        final String path = request.getDescription(false);
        body.put(TIMESTAMP, Instant.now());
        body.put(STATUS, status.value());
        body.put(ERRORS, errors);
        body.put(TYPE, exception.getClass().getSimpleName());
        body.put(PATH, path);
        body.put(MESSAGE, getMessageForStatus(status, exception.getMessage()));
        final String errorsMessage = !CollectionUtils.isEmpty(errors) ?
                errors.stream().filter(string -> !string.isEmpty()).collect(Collectors.joining(LIST_JOIN_DELIMITER))
                : status.getReasonPhrase();
        log.error(ERRORS_FOR_PATH, errorsMessage, path);
        return new ResponseEntity<>(body, status);
    }

    private String getMessageForStatus(HttpStatus status, String message) {
        if (nonNull(message) && !message.isBlank()) {
            return message;
        }
        return switch (status) {
            case UNAUTHORIZED -> ACCESS_DENIED;
            case BAD_REQUEST -> INVALID_REQUEST;
            default -> status.getReasonPhrase();
        };
    }
}