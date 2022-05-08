package com.example.bugtracker.service;

import com.example.bugtracker.model.Role;
import org.springframework.stereotype.Service;


@Service
public interface RoleService {
    Role findById(Long id) ;
}
