package com.example.bugtracker.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@Schema(description = "Error DTO")
public class ErrorDto {
    @Schema(description = "Error")
    private String error;
    @Schema(description = "Message")
    private String message;
    @Schema(description = "Status")
    private int status;
    @Schema(description = "Timestamp")
    private LocalDateTime timestamp;
    @Schema(description = "Voliation list")
    private List<Violation> violations;
}
