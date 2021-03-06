package com.example.bugtracker.controller;

import com.example.bugtracker.dto.user.UserCreateDto;
import com.example.bugtracker.dto.user.UserDto;
import com.example.bugtracker.dto.user.UserLoginDto;
import com.example.bugtracker.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;


@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class AuthController {
    private final AuthService service;

    @Operation(summary = "Register new users")
    @PostMapping("/signup")
    public UserDto registerUser(@Valid @RequestBody UserCreateDto userCreateDto) {
        return service.registerUser(userCreateDto);
    }

    @Operation(summary = "Login already existed users")
    @PostMapping("/signin")
    public String authenticateUser(@Valid @RequestBody UserLoginDto userLoginDto) {
        return service.authenticateUser(userLoginDto);
    }
}
