package com.example.bugtracker.service.impl;

import com.example.bugtracker.dto.ticket.TicketCreateDto;
import com.example.bugtracker.enums.SeverityTicket;
import com.example.bugtracker.exception.NotFoundException;
import com.example.bugtracker.model.Ticket;
import com.example.bugtracker.model.User;
import com.example.bugtracker.repository.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {
    @Mock
    UserRepository repository;

    @InjectMocks
    UserServiceImpl service;

    @Test
    void findNotExistedUserByEmail_thenThrowException(){
        String email = "email@email.com";

        when(repository.findByEmail(email)).thenReturn(Optional.empty());
        Assertions.assertThatThrownBy(() -> service.findByEmail(email))
                .isInstanceOf(NotFoundException.class);
        Mockito.verify(repository, Mockito.times(1)).findByEmail(email);
    }

    @Test
    void findNotExistedUserById_thenThrowException(){
        Long userId = 1L;

        when(repository.findById(userId)).thenReturn(Optional.empty());
        Assertions.assertThatThrownBy(() -> service.getDtoById(userId))
                .isInstanceOf(NotFoundException.class);
        Mockito.verify(repository, Mockito.times(1)).findById(userId);
    }

    @Test
    void saveUser() {
        User user = User.builder()
                .firstName("user")
                .build();

        service.save(user);

        ArgumentCaptor<User> ticketArgumentCaptor = ArgumentCaptor.forClass(User.class);
        Mockito.verify(repository, Mockito.times(1))
                .save(ticketArgumentCaptor.capture());
        User captorUser = ticketArgumentCaptor.getValue();

        assertEquals(user, captorUser);
    }
}