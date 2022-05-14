package com.example.bugtracker.service;

import com.example.bugtracker.model.Role;
import org.springframework.stereotype.Service;

/**
 * Service provides operations on role
 */
public interface RoleService {
    /**
     * Find role by id
     *
     * @param id role's id
     * @return Role
     */
    Role findById(Long id) ;
}
