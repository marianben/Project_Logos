package com.cinema.dto.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
public class ValidationExceptionResponse {

    private String field;
    private String event;
    private List<String> errors;
    private LocalDateTime timestamp;

    public ValidationExceptionResponse(String event, List<String> errors) {
        this.field = field;
        this.event = event;
        this.errors = errors;
        this.timestamp = LocalDateTime.now();
    }
}