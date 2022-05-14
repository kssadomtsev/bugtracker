package com.example.bugtracker.dto.ticket;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Schema(description = "Comment of ticket DTO object")
public class CommentDto {
    @Schema(description = "Unique id")
    private Long id;
    @Schema(description = "Comment message (text)")
    private String message;
    @Schema(description = "Creation timestamp")
    private LocalDateTime createdAt;
    @Schema(description = "Author email")
    private String author;
}
