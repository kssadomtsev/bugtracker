package com.example.bugtracker.service.impl;

import com.example.bugtracker.exception.NotFoundException;
import com.example.bugtracker.model.Company;
import com.example.bugtracker.repository.CompanyRepository;
import com.example.bugtracker.service.CompanyService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CompanyServiceImpl implements CompanyService {
    private static final String COMPANY_NOT_FOUND = "Company not found by id %d";

    private final CompanyRepository repository;

    @Override
    public Company findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format(COMPANY_NOT_FOUND, id)));
    }
}
