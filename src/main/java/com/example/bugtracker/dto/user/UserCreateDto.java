package com.example.bugtracker.dto.user;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@Builder
@Schema(description = "User DTO create object")
public class UserCreateDto {
    @Email(message = "Incorrect email format")
    @NotEmpty(message = "Field email is mandatory")
    @Schema(description = "Email")
    private String email;

    @NotEmpty(message = "Field first name is mandatory")
    @Schema(description = "First name")
    private String firstName;

    @Schema(description = "Last name")
    private String lastName;

    @NotEmpty(message = "Field password is mandatory")
    @Schema(description = "Password")
    private String password;

    @NotEmpty(message = "Field roles is mandatory")
    @Schema(description = "List of roles id")
    private List<Long> roles;

    @NotNull(message = "Field company is mandatory")
    @Schema(description = "Company id")
    private Long companyId;
}
