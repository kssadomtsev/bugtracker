package com.example.bugtracker.dto.ticket;

import com.example.bugtracker.dto.user.UserDto;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class TicketDto {
    private Long id;
    private String severity;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime solvedAt;
    private String reporter;
    private String responsible;
    private String status;
    private String description;
    private List<CommentDto> comments;
}
