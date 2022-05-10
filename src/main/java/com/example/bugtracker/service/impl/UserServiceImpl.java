package com.example.bugtracker.service.impl;

import com.example.bugtracker.dto.user.UserDto;
import com.example.bugtracker.exception.NotFoundException;
import com.example.bugtracker.mapping.MappingService;
import com.example.bugtracker.model.User;
import com.example.bugtracker.repository.UserRepository;
import com.example.bugtracker.service.UserService;
import com.example.bugtracker.service.impl.filter.UserFilterService;
import com.example.bugtracker.specification.criteria.UserCriteria;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private static final String USER_NOT_FOUND_BY_EMAIL = "User not found by email %s";
    private static final String USER_NOT_FOUND_BY_ID = "User not found by id %d";

    private final UserRepository repository;
    private final UserFilterService userFilterService;
    private final MappingService mappingService;

    @Override
    public User findByEmail(String email) {
        return repository.findByEmail(email)
                .orElseThrow(() -> new NotFoundException(String.format(USER_NOT_FOUND_BY_EMAIL, email)));
    }

    @Override
    public boolean existsByEmail(String email) {
        return repository.existsByEmail(email);
    }

    @Override
    @Transactional
    public User save(User user) {
        return repository.save(user);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @Override
    public Page<UserDto> getUserListSortedAndPageable(Pageable pageable, UserCriteria criteria) {
        return userFilterService.getUserListSortedAndPageable(pageable, criteria);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @Override
    public UserDto getDtoById(Long id) {
        return mappingService.map(repository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format(USER_NOT_FOUND_BY_ID, id))), UserDto.class);
    }
}
