package com.example.bugtracker.dto.ticket;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Comment of ticket DTO create object")
public class CommentCreateDto {
    @NotEmpty(message = "Field message is mandatory")
    @Length(max = 4000, message = "Maximum field length is 4000")
    @Schema(description = "Comment message (text)")
    private String message;
}
