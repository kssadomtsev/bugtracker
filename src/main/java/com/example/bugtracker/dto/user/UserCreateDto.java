package com.example.bugtracker.dto.user;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@Builder
public class UserCreateDto {
    @Email(message = "Incorrect email format")
    @NotEmpty(message = "Field email is mandatory")
    private String email;

    @NotEmpty(message = "Field first name is mandatory")
    private String firstName;

    private String lastName;

    @NotEmpty(message = "Field password is mandatory")
    private String password;

    @NotEmpty(message = "Field roles is mandatory")
    private List<Long> roles;

    @NotNull(message = "Field company is mandatory")
    private Long companyId;
}
