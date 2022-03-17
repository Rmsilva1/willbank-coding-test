package com.willbank.codingtest.config;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.willbank.codingtest.model.ValidationError;
import com.willbank.codingtest.model.exception.ApiException;
import com.willbank.codingtest.model.exception.NotFoundException;
import com.willbank.codingtest.model.exception.ValidationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class ControllerAdvice {

    private static final Logger logger = LoggerFactory.getLogger(ControllerAdvice.class);

    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public List<ValidationError> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex) {
        if (ex.getCause() instanceof InvalidFormatException) {
            InvalidFormatException invalidFormatException = (InvalidFormatException) ex.getCause();

            String fieldPath = invalidFormatException.getPath()
                    .stream()
                    .map(JsonMappingException.Reference::getFieldName)
                    .collect(Collectors.joining("."));

            List<ValidationError> errors = Collections.singletonList(new ValidationError(fieldPath,
                    "invalid",
                    invalidFormatException.getValue().toString()));

            logger.error(String.format("handleHttpMessageNotReadableException errors: %s", errors));
            return errors;
        }
        logger.error("handleHttpMessageNotReadableException", ex);
        return Collections.singletonList(new ValidationError("request", "invalid"));
    }

    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public List<ValidationError> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        List<ValidationError> errors = exception
                .getBindingResult()
                .getFieldErrors()
                .stream()
                .map(field -> new ValidationError(field.getField(),
                        field.getDefaultMessage(),
                        Optional.ofNullable(field.getRejectedValue()).map(Object::toString).orElse("")))
                .collect(Collectors.toList());

        logger.error("handleMethodArgumentNotValidException errors: " + errors);
        return errors;
    }

    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    @ExceptionHandler(ValidationException.class)
    public List<ValidationError> handleValidationException(ValidationException ex) {
        logger.error("handleValidationException errors: " + ex.getValidationErrors());
        return ex.getValidationErrors();
    }

    @ExceptionHandler(ApiException.class)
    public ResponseEntity<?> handleApiException(ApiException ex) {
        if (ex.logError()) {
            logger.error("handleApiException", ex);
        }
        return ResponseEntity.status(ex.getStatus()).build();
    }

    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public List<ValidationError> handleMissingServletRequestParameterException(MissingServletRequestParameterException ex) {
        logger.error("handleMissingServletRequestParameterException missing parameter: " + ex.getParameterName());
        return Collections.singletonList(new ValidationError(ex.getParameterName(), "invalid"));
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public void handleException(Exception ex) {
        logger.error("handleException", ex);
    }

    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public List<ValidationError> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException ex) {
        logger.error(String.format("handleMethodArgumentTypeMismatchException name: %s, parameter: %s, value: %s",
                ex.getName(),
                ex.getParameter().getParameterName(),
                ex.getValue()));

        return Collections.singletonList(new ValidationError(ex.getName(), "invalid"));
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public void handleNotFoundException(NotFoundException ex) {
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MissingRequestHeaderException.class)
    public void handleMissingRequestHeaderException(MissingRequestHeaderException ex) {
        logger.error("handleMissingRequestHeaderException missing header:" + ex.getMessage());
    }
}
