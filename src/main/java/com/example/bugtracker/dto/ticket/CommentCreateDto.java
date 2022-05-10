package com.example.bugtracker.dto.ticket;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentCreateDto {
    @NotEmpty(message = "Field message is mandatory")
    @Length(max = 4000, message = "Maximum field length is 4000")
    private String message;
}
