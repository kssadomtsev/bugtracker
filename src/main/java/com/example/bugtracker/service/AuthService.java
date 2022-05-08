package com.example.bugtracker.service;

import com.example.bugtracker.dto.user.UserCreateDto;
import com.example.bugtracker.dto.user.UserDto;
import com.example.bugtracker.dto.user.UserLoginDto;

public interface AuthService {
    UserDto registerUser(UserCreateDto userCreateDto);
    String authenticateUser(UserLoginDto userLoginDto);
}
