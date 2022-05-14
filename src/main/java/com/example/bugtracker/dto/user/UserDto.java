package com.example.bugtracker.dto.user;

import com.example.bugtracker.dto.IdNameDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
@Schema(description = "User DTO object")
public class UserDto {
    @Schema(description = "Email")
    private String email;
    @Schema(description = "First name")
    private String firstName;
    @Schema(description = "Last name")
    private String lastName;
    @Schema(description = "User's roles")
    private List<IdNameDTO> roles;
    @Schema(description = "Company")
    private IdNameDTO company;
}
