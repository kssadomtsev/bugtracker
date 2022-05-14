package com.example.bugtracker.service.impl;

import com.example.bugtracker.model.Role;
import com.example.bugtracker.model.User;
import com.example.bugtracker.service.UserService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collections;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserDetailsServiceImplTest {
    @Mock
    UserService userService;

    @InjectMocks
    UserDetailsServiceImpl service;


    @Test
    void loadUserDetails_When_LoginExist() {
        String login = "email@email.com";
        String password = "password";
        String roleName = "ADMIN";

        Role role = Role.builder()
                .name(roleName)
                .build();
        User user = User.builder()
                .email(login)
                .password(password)
                .roles(Set.of(role))
                .build();


        when(userService.findByEmail(login))
                .thenReturn(user);

        UserDetails userDetails = service.loadUserByUsername(login);

        Assertions.assertThat(userDetails)
                .extracting(UserDetails::getUsername, UserDetails::getPassword)
                .containsExactly(login, password);

        Assertions.assertThat(userDetails)
                .extracting(UserDetails::getAuthorities)
                .extracting(o-> o.stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
                .asList()
                .contains(roleName);
    }
}