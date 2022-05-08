package com.example.bugtracker.dto.user;

import com.example.bugtracker.dto.IdNameDTO;
import lombok.Data;

import java.util.List;

@Data
public class UserDto {
    private String email;
    private String firstName;
    private String lastName;
    private List<IdNameDTO> roles;
    private IdNameDTO company;
}
