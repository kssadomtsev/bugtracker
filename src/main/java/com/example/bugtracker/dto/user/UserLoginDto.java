package com.example.bugtracker.dto.user;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
@Builder
@Schema(description = "User login DTO")
public class UserLoginDto {
    @NotEmpty(message = "Login is mandatory")
    @Schema(description = "Login")
    private String login;

    @NotEmpty(message = "Password is mandatory")
    @Schema(description = "Password")
    private String password;
}
