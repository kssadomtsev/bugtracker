package com.example.bugtracker.dto.ticket;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@Builder
@Schema(description = "DTO object for operation of verifying ticket")
public class TicketVerifyDto {
    @NotNull(message = "Field isCorrect is mandatory")
    @Schema(description = "Ticket was solved correct(true) or no(false)")
    private boolean isCorrect;

    @NotEmpty(message = "Field message is mandatory")
    @Length(max = 4000, message = "Maximum field length is 4000")
    @NotNull(message = "Operation message")
    private String message;
}
