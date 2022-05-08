package com.example.bugtracker.service.impl;

import com.example.bugtracker.exception.NotFoundException;
import com.example.bugtracker.model.Role;
import com.example.bugtracker.repository.RoleRepository;
import com.example.bugtracker.service.RoleService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RoleServiceImpl implements RoleService {
    private static final String ROLE_NOT_FOUND = "Role not found by id %d";

    private final RoleRepository repository;

    @Override
    public Role findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format(ROLE_NOT_FOUND, id)));
    }
}
