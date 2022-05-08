package com.example.bugtracker.service;

import com.example.bugtracker.model.Company;
import org.springframework.stereotype.Service;


@Service
public interface CompanyService {
    Company findById(Long id) ;
}
