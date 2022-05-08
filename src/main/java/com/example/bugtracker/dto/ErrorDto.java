package com.example.bugtracker.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class ErrorDto {
    private String error;
    private String message;
    private int status;
    private LocalDateTime timestamp;
    private List<Violation> violations;
}
