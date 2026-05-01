package com.adrian.resumeserver.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * GlobalExceptionHandler — catches exceptions thrown anywhere in the app
 * and returns consistent, clean JSON error responses.
 *
 * @RestControllerAdvice tells Spring to apply this handler to ALL controllers.
 * @ExceptionHandler tells Spring which exception type each method handles.
 *
 * Without this, Spring returns its own verbose error format.
 * With this, every error looks the same — predictable and professional.
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Handles @Valid validation failures.
     * Triggered when a required field is missing or fails a constraint.
     * Returns HTTP 400 with a map of field → error message.
     * <p>
     * Example response:
     * {
     * "status": 400,
     * "error": "Validation Failed",
     * "messages": {
     * "email": "Email is required",
     * "fullName": "Full name is required"
     * },
     * "timestamp": "2026-04-30T21:00:00"
     * }
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleValidationErrors(
            MethodArgumentNotValidException ex) {

        Map<String, String> fieldErrors = new HashMap<>();
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            fieldErrors.put(error.getField(), error.getDefaultMessage());
        }

        Map<String, Object> response = new HashMap<>();
        response.put("status", HttpStatus.BAD_REQUEST.value());
        response.put("error", "Validation Failed");
        response.put("messages", fieldErrors);
        response.put("timestamp", LocalDateTime.now());

        return ResponseEntity.badRequest().body(response);
    }

    /**
     * Handles any unexpected exception not caught elsewhere.
     * Returns HTTP 500 with a generic message — never expose internal details.
     * <p>
     * Example response:
     * {
     * "status": 500,
     * "error": "Internal Server Error",
     * "message": "An unexpected error occurred",
     * "timestamp": "2026-04-30T21:00:00"
     * }
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handleGenericError(Exception ex) {
        // Temporary: print the real error so we can see what's happening
        ex.printStackTrace();

        Map<String, Object> response = new HashMap<>();
        response.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
        response.put("error", "Internal Server Error");
        response.put("message", "An unexpected error occurred");
        response.put("timestamp", LocalDateTime.now());

        return ResponseEntity.internalServerError().body(response);
    }

/*

Key things to notice:

@RestControllerAdvice — applies this handler globally to every controller in the app automatically
@ExceptionHandler(MethodArgumentNotValidException.class) — specifically catches @Valid failures
fieldErrors map — loops through every failed field and collects the error messages you wrote in the DTOs like "Email is required"
@ExceptionHandler(Exception.class) — catches anything else that wasn't handled — a safety net
The response never exposes the actual Java exception message — only a generic safe message



 */



}
