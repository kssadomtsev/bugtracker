package com.example.bugtracker.dto.ticket;

import com.example.bugtracker.enums.SeverityTicket;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class TicketCreateDto {
    @NotNull(message = "Field severity is mandatory")
    private SeverityTicket severity;

    @NotEmpty(message = "Field description is mandatory")
    @Length(max = 4000, message = "Maximum field length is 4000")
    private String description;
}
