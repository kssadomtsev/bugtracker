package com.example.bugtracker.dto.ticket;

import lombok.Builder;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@Builder
public class TicketAssignDto {
    @NotNull(message = "Field responsible is mandatory")
    private String responsible;

    @NotEmpty(message = "Field message is mandatory")
    @Length(max = 4000, message = "Maximum field length is 4000")
    private String message;
}
