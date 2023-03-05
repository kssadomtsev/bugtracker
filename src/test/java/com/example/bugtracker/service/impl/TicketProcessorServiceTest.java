package com.example.bugtracker.service.impl;

import com.example.bugtracker.dto.ticket.TicketDto;
import com.example.bugtracker.dto.ticket.TicketMultipleAssignDto;
import com.example.bugtracker.exception.DuplicateEntityException;
import com.example.bugtracker.exception.IncorrectFlowException;
import com.example.bugtracker.model.Ticket;
import com.example.bugtracker.repository.TicketRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Assert;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

//@RunWith(SpringRunner.class)
@SpringBootTest(classes = {TicketProcessorService.class})
class TicketProcessorServiceTest {

    @Autowired
    TicketProcessorService service;

    @Test
    void assignMultiple() {
        TicketMultipleAssignDto multipleAssignDto = TicketMultipleAssignDto.builder()
                .ticketIds(List.of(1L, 7L))
                .responsible("bob_admin@rd.com")
                .message("Ticket assignment")
                .build();

        List<TicketDto> tickets = service.assignMultiple(multipleAssignDto);

        for (TicketDto ticket : tickets) {
            Assertions.assertThat(ticket.getStatus()).isEqualTo("ASSIGNMENT");
        }
    }
    @Test
    void assignMultipleNotCorrectRole() {
        TicketMultipleAssignDto multipleAssignDto = TicketMultipleAssignDto.builder()
                .ticketIds(List.of(1L, 7L))
                .responsible("nick@customer.com")
                .message("Ticket assignment")
                .build();

        Assertions.assertThatThrownBy(() -> service.assignMultiple(multipleAssignDto))
                .isInstanceOf(IncorrectFlowException.class);

    }

}