package com.administracion.administration.configs;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.administracion.administration.auth.exceptions.CredencialesInvalidasException;

import java.time.LocalDateTime;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CredencialesInvalidasException.class)
    public ResponseEntity<?> credencialesInvalidas(CredencialesInvalidasException ex) {
        return ResponseEntity.status(401).body(
            Map.of(
                "timestamp", LocalDateTime.now(),
                "status", 401,
                "mensaje", ex.getMessage()
            )
        );
    }
}
