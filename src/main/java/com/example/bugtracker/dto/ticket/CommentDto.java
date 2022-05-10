package com.example.bugtracker.dto.ticket;

import com.example.bugtracker.dto.user.UserDto;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CommentDto {
    private Long id;
    private String message;
    private LocalDateTime createdAt;
    private String author;
}
