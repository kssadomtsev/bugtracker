package com.example.bugtracker.service.impl;

import com.example.bugtracker.exception.NotFoundException;
import com.example.bugtracker.repository.TicketRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;


import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TicketServiceImplTest {
    @Mock
    TicketRepository repository;

    @InjectMocks
    TicketServiceImpl service;

    @Test
    void deleteNotExistedTicket_thenThrowException() {
        Long userId = 1L;

        when(repository.existsById(userId)).thenReturn(false);

        Assertions.assertThatThrownBy(() -> service.deleteById(userId))
                .isInstanceOf(NotFoundException.class);
        Mockito.verify(repository, Mockito.times(1)).existsById(userId);
    }
}