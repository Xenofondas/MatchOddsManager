package com.matchoddsmanager.match_odds_manager.config;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.matchoddsmanager.match_odds_manager.dto.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ApiResponse<Object>> handleJsonParseException(HttpMessageNotReadableException ex) {

        Throwable cause = ex.getCause();
        Map<String, String> errors = null;

        if (cause instanceof InvalidFormatException invalidFormatException) {
            String fieldName = invalidFormatException.getPath().getFirst().getFieldName();
            String wrongValue = invalidFormatException.getValue() != null
                    ? invalidFormatException.getValue().toString()
                    : "null";

            errors = Map.of(
                    fieldName, "Invalid value: '" + wrongValue + "'"
            );
        }

        ApiResponse<Object> response = ApiResponse.builder()
                .status("ERROR")
                .message("Invalid request JSON")
                .errors(errors)
                .data(null)
                .build();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ApiResponse<Object>> handleIllegalArgument(IllegalArgumentException ex) {
        ApiResponse<Object> response = ApiResponse.builder()
                .status("ERROR")
                .message(ex.getMessage())
                .data(null)
                .errors(null)
                .build();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<Object>> handleValidationException(MethodArgumentNotValidException ex) {

        Map<String, String> errorMap = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .collect(Collectors.toMap(
                        fieldError -> fieldError.getField(),
                        fieldError -> fieldError.getDefaultMessage(),
                        (existing, replacement) -> existing
                ));

        ApiResponse<Object> response = ApiResponse.builder()
                .status("ERROR")
                .message("Validation failed")
                .errors(errorMap)
                .data(null)
                .build();

        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<Object>> handleAllOtherExceptions(Exception ex) {

        ApiResponse<Object> response = ApiResponse.builder()
                .status("ERROR")
                .message(ex.getMessage() != null ? ex.getMessage() : "Unexpected error occurred")
                .data(null)
                .errors(null)
                .build();

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }
}