package com.example.bugtracker.service.impl;

import com.example.bugtracker.dto.user.UserCreateDto;
import com.example.bugtracker.dto.user.UserDto;
import com.example.bugtracker.dto.user.UserLoginDto;
import com.example.bugtracker.exception.DuplicateEntityException;
import com.example.bugtracker.mapping.MappingService;
import com.example.bugtracker.model.Role;
import com.example.bugtracker.model.User;
import com.example.bugtracker.security.jwt.JwtTokenUtil;
import com.example.bugtracker.service.AuthService;
import com.example.bugtracker.service.CompanyService;
import com.example.bugtracker.service.RoleService;
import com.example.bugtracker.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * AuthService interface implementation
 */

@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {
    private static final String DUPLICATE_EMAIL = "User with email %s already exists";
    private final AuthenticationManager authenticationManager;
    private final JwtTokenUtil jwtTokenUtil;

    private final UserService userService;
    private final RoleService roleService;
    private final CompanyService companyService;
    private final PasswordEncoder encoder;
    private final MappingService mappingService;

    @Override
    public UserDto registerUser(UserCreateDto userCreateDto) {
        if (userService.existsByEmail(userCreateDto.getEmail())){
            throw new DuplicateEntityException(String.format(DUPLICATE_EMAIL, userCreateDto.getEmail()));
        }
        User user = mappingService.map(userCreateDto, User.class);
        user.setPassword(encoder.encode(userCreateDto.getPassword()));
        user.setRoles(getRoles(userCreateDto.getRoles()));
        user.setCompany(Objects.isNull(userCreateDto.getCompanyId()) ? null : companyService.findById(userCreateDto.getCompanyId()));
        return mappingService.map(userService.save(user), UserDto.class);
    }

    @Override
    public String authenticateUser(UserLoginDto userLoginDto) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userLoginDto.getLogin(), userLoginDto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return jwtTokenUtil.generateJwtToken(authentication);
    }

    /**
     * Get list or role from given list of their ids
     *
     * @param roleIds list of role's id
     * @return list or role object
     */
    private Set<Role> getRoles(List<Long> roleIds) {
        return roleIds.stream().map(roleService::findById)
                .collect(Collectors.toSet());
    }
}
