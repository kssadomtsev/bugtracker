package com.example.bugtracker.dto.ticket;

import com.example.bugtracker.enums.SeverityTicket;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@Builder
@Schema(description = "Ticket DTO create object")
public class TicketCreateDto {
    @NotNull(message = "Field severity is mandatory")
    @Schema(description = "Ticket severity")
    private SeverityTicket severity;

    @NotEmpty(message = "Field description is mandatory")
    @Length(max = 4000, message = "Maximum field length is 4000")
    @Schema(description = "Ticket description")
    private String description;
}
