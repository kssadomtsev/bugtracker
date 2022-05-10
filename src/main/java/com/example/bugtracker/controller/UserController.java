package com.example.bugtracker.controller;

import com.example.bugtracker.dto.user.UserDto;
import com.example.bugtracker.service.UserService;
import com.example.bugtracker.specification.criteria.UserCriteria;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {
    private final UserService service;

    @GetMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public Page<UserDto> getUserList(@PageableDefault(sort = "id", direction = Sort.Direction.ASC) Pageable pageable,
                                     UserCriteria criteria) {
        return service.getUserListSortedAndPageable(pageable, criteria);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public UserDto getUser(@PathVariable Long id) {
        return service.getDtoById(id);
    }
}
