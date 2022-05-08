package com.example.bugtracker.dto.user;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class UserLoginDto {
    @NotEmpty(message = "Login is mandatory")
    private String login;

    @NotEmpty(message = "Password is mandatory")
    private String password;
}
