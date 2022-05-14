package com.example.bugtracker.service;

import com.example.bugtracker.dto.user.UserCreateDto;
import com.example.bugtracker.dto.user.UserDto;
import com.example.bugtracker.dto.user.UserLoginDto;

/**
 * Service provides user authentication operations
 */

public interface AuthService {
    /**
     * Register new user account to application
     *
     * @param userCreateDto user create DTO
     * @return user created DTO
     */
    UserDto registerUser(UserCreateDto userCreateDto);

    /**
     * Login existed user to application
     *
     * @param userLoginDto user login DTO
     * @return JWT token
     */
    String authenticateUser(UserLoginDto userLoginDto);
}
