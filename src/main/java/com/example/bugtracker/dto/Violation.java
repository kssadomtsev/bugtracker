package com.example.bugtracker.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
@Schema(description = "Violation DTO object")
public class Violation {
    @Schema(description = "Field name with violation")
    private final String field;
    @Schema(description = "Violation message")
    private final String message;
}
