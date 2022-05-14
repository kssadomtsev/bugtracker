package com.example.bugtracker.service;

import com.example.bugtracker.model.Company;
import org.springframework.stereotype.Service;

/**
 * Service provides operations on company
 */

public interface CompanyService {
    /**
     * Find company by id
     *
     * @param id company's id
     * @return Company
     */
    Company findById(Long id) ;
}
