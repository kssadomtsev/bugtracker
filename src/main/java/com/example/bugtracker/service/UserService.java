package com.example.bugtracker.service;

import com.example.bugtracker.dto.user.UserDto;
import com.example.bugtracker.model.User;
import com.example.bugtracker.specification.criteria.UserCriteria;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
public interface UserService {
    User findByEmail(String email);
    boolean existsByEmail(String email);
    User save(User user);
    Page<UserDto> getUserListSortedAndPageable(Pageable pageable, UserCriteria criteria);
    UserDto findById(Long id);
}
