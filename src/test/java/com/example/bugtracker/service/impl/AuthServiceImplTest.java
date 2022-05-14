package com.example.bugtracker.service.impl;

import com.example.bugtracker.dto.ticket.TicketCreateDto;
import com.example.bugtracker.dto.user.UserCreateDto;
import com.example.bugtracker.enums.SeverityTicket;
import com.example.bugtracker.exception.DuplicateEntityException;
import com.example.bugtracker.exception.NotFoundException;
import com.example.bugtracker.service.UserService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AuthServiceImplTest {
    @InjectMocks
    AuthServiceImpl service;

    @Mock
    UserService userService;

    @Test
    void registerUserWithAlreadyExistedEmail_thenThrowException() {
        String email = "email@email.com";
        UserCreateDto newUserDto = UserCreateDto.builder()
                .email(email)
                .build();

        when(userService.existsByEmail(email)).thenReturn(true);

        Assertions.assertThatThrownBy(() -> service.registerUser(newUserDto))
                .isInstanceOf(DuplicateEntityException.class);
        Mockito.verify(userService, Mockito.times(1)).existsByEmail(email);
    }
}