package com.example.bugtracker.controller;

import com.example.bugtracker.AbstractMvcInit;
import com.example.bugtracker.dto.user.UserCreateDto;
import com.example.bugtracker.dto.user.UserLoginDto;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


class AuthControllerTest extends AbstractMvcInit {
    private final String authPath = "/auth";

    @Test
    void registerUserWithIncorrectEmail_thenStatus400() throws Exception {
        UserCreateDto newUser = UserCreateDto.builder()
                .email("email-email.com")
                .firstName("User")
                .password("password")
                .companyId(1L)
                .roles(List.of(4L))
                .build();

        mvc.perform(MockMvcRequestBuilders.post(authPath + "/signup")
                        .contentType(MediaType.APPLICATION_JSON_VALUE).content(mapToJson(newUser)))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.violations.[0].message", Matchers.is("Incorrect email format")));
    }

    @Test
    void registerUser_thenStatus200() throws Exception {
        UserCreateDto newUser = UserCreateDto.builder()
                .email("email@email.com")
                .firstName("User")
                .password("password")
                .companyId(1L)
                .roles(List.of(4L))
                .build();

        mvc.perform(MockMvcRequestBuilders.post(authPath + "/signup")
                        .contentType(MediaType.APPLICATION_JSON_VALUE).content(mapToJson(newUser)))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void registerUserWithoutEmail_thenStatus400() throws Exception {
        UserCreateDto newUser = UserCreateDto.builder()
                .firstName("User")
                .password("password")
                .companyId(1L)
                .roles(List.of(4L))
                .build();

        mvc.perform(MockMvcRequestBuilders.post(authPath + "/signup")
                        .contentType(MediaType.APPLICATION_JSON_VALUE).content(mapToJson(newUser)))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.violations.[0].message", Matchers.is("Field email is mandatory")));
    }

    @Test
    void registerDuplicateEmail_thenStatus409() throws Exception {
        UserCreateDto newUser = UserCreateDto.builder()
                .email("alice_dev@rd.com")
                .firstName("User")
                .password("password")
                .companyId(1L)
                .roles(List.of(4L))
                .build();

        mvc.perform(MockMvcRequestBuilders.post(authPath + "/signup")
                        .contentType(MediaType.APPLICATION_JSON_VALUE).content(mapToJson(newUser)))
                .andDo(print())
                .andExpect(status().isConflict())
                .andExpect(jsonPath("$.message", Matchers.is("User with email alice_dev@rd.com already exists")));
    }

    @Test
    void signinWithBadCredentials_thenStatus500() throws Exception {
        UserLoginDto badUser = UserLoginDto.builder()
                .login("alice_dev@rd.com")
                .password("password1")
                .build();

        mvc.perform(MockMvcRequestBuilders.post(authPath + "/signin")
                        .contentType(MediaType.APPLICATION_JSON_VALUE).content(mapToJson(badUser)))
                .andDo(print())
                .andExpect(status().is5xxServerError())
                .andExpect(jsonPath("$.message", Matchers.is("Bad credentials")));
    }

    @Test
    void signinUnkownUser_thenStatus500() throws Exception {
        UserLoginDto badUser = UserLoginDto.builder()
                .login("alice_dev1@rd.com")
                .password("password1")
                .build();

        mvc.perform(MockMvcRequestBuilders.post(authPath + "/signin")
                        .contentType(MediaType.APPLICATION_JSON_VALUE).content(mapToJson(badUser)))
                .andDo(print())
                .andExpect(status().is5xxServerError())
                .andExpect(jsonPath("$.message", Matchers.is("User not found by email alice_dev1@rd.com")));
    }
}