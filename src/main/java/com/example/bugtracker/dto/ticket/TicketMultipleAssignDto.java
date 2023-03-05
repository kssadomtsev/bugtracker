package com.example.bugtracker.dto.ticket;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@Builder
@Schema(description = "DTO object for operation of assigning ticket to executor(responsible)")
public class TicketMultipleAssignDto {
    @NotEmpty(message = "Field is mandatory")
    @Schema(description = "Tickets ids")
    List<Long> ticketIds;

    @NotNull(message = "Responsible email")
    @Schema(description = "Unique id")
    private String responsible;

    @NotEmpty(message = "Field message is mandatory")
    @Length(max = 4000, message = "Maximum field length is 4000")
    @NotNull(message = "Operation message")
    private String message;
}
