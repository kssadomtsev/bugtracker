package com.example.bugtracker.dto.user;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
@Builder
public class UserLoginDto {
    @NotEmpty(message = "Login is mandatory")
    private String login;

    @NotEmpty(message = "Password is mandatory")
    private String password;
}
