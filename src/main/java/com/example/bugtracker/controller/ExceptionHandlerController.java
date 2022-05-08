package com.example.bugtracker.controller;

import com.example.bugtracker.dto.ErrorDto;
import com.example.bugtracker.dto.Violation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

import static java.util.stream.Collectors.toList;

@RestController
@ControllerAdvice
@Slf4j
public class ExceptionHandlerController {

    @ExceptionHandler
    public final ResponseEntity<ErrorDto> handleCommonException(Exception ex, WebRequest request) {
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        ResponseStatus responseStatus = ex.getClass().getAnnotation(ResponseStatus.class);
        if (responseStatus != null) {
            log.error(ex.getMessage());
            status = responseStatus.value();
        }
        return new ResponseEntity<>(ErrorDto.builder()
                .message(ex.getMessage())
                .error(status.name())
                .timestamp(LocalDateTime.now())
                .status(status.value())
                .build(), status);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorDto> handleConstraintViolation(
            final MethodArgumentNotValidException exception,
            final NativeWebRequest request
    ) {
        return ResponseEntity
                .badRequest()
                .body(ErrorDto.builder()
                        .status(HttpStatus.BAD_REQUEST.value())
                        .error("Validation error")
                        .timestamp(LocalDateTime.now())
                        .violations(exception.getBindingResult().getFieldErrors().stream()
                                .map(this::createViolation)
                                .collect(toList()))
                        .build());
    }

    @ExceptionHandler(value = {AccessDeniedException.class})
    public final ResponseEntity<ErrorDto> handleAccessDeniedException(Exception ex, WebRequest request) {
        return new ResponseEntity<>(ErrorDto.builder()
                .message(ex.getMessage())
                .error(HttpStatus.FORBIDDEN.name())
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.FORBIDDEN.value())
                .build(), HttpStatus.FORBIDDEN);
    }

    private Violation createViolation(final FieldError violation) {
        return new Violation(violation.getField(), violation.getDefaultMessage());
    }
}
