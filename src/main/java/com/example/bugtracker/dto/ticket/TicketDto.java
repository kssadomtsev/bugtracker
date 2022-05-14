package com.example.bugtracker.dto.ticket;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Schema(description = "Ticket DTO object")
public class TicketDto {
    @Schema(description = "Unique id")
    private Long id;
    @Schema(description = "Ticket severity")
    private String severity;
    @Schema(description = "Creation timestamp")
    private LocalDateTime createdAt;
    @Schema(description = "Update timestamp")
    private LocalDateTime updatedAt;
    @Schema(description = "Solve timestamp")
    private LocalDateTime solvedAt;
    @Schema(description = "Reporter email")
    private String reporter;
    @Schema(description = "Responsible email")
    private String responsible;
    @Schema(description = "Ticket status")
    private String status;
    @Schema(description = "Ticket description")
    private String description;
    @Schema(description = "Ticket comments")
    private List<CommentDto> comments;
}
