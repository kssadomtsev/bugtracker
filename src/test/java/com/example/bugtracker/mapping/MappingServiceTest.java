package com.example.bugtracker.mapping;

import com.example.bugtracker.dto.user.UserDto;
import com.example.bugtracker.model.User;
import com.example.bugtracker.service.impl.UserDetailsServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class MappingServiceTest {
    @InjectMocks
    MappingService service;
    @Spy
    ModelMapper modelMapper;

    @Test
    void mapOneClassToAnother_Test() {
        String firstName = "name";
        String email = "email@email.com";
        User user = User.builder()
                .firstName(firstName)
                .email(email)
                .build();

        UserDto userDto = service.map(user, UserDto.class);

        Assertions.assertThat(userDto)
                .extracting(UserDto::getEmail, UserDto::getFirstName)
                .containsExactly(email, firstName);
    }
}