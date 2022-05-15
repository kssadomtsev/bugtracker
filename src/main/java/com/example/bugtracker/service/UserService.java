package com.example.bugtracker.service;

import com.example.bugtracker.dto.user.UserDto;
import com.example.bugtracker.model.User;
import com.example.bugtracker.specification.criteria.UserCriteria;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * Service provides operations on user
 */
public interface UserService {
    /**
     * Find user by email
     *
     * @param email user's email
     * @return User
     */
    User findByEmail(String email);

    /**
     * Check user's record existing by its email
     *
     * @param email user's email
     * @return true/false
     */
    boolean existsByEmail(String email);

    /**
     * Save user
     *
     * @param user
     * @return saved user
     */
    User save(User user);

    /**
     * Get pageable list of user
     *
     * @param pageable page
     * @param criteria filtered criteria
     * @return pageable list of user
     */
    Page<UserDto> getUserListSortedAndPageable(Pageable pageable, UserCriteria criteria);

    /**
     * Get user by id
     *
     * @param id user's id
     * @return user's DTO
     */
    UserDto getDtoById(Long id);
}
