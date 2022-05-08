package com.example.bugtracker.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class Violation {
    private final String field;
    private final String message;
}
