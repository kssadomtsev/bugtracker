package com.example.bugtracker.service.impl;

import com.example.bugtracker.model.User;
import com.example.bugtracker.security.UserAwareUserDetails;
import com.example.bugtracker.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * UserDetailsService interface implementation
 */

@Service
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserService userService;

    /**
     * Get UserDetails user implementation by username
     *
     * @param username username
     * @return UserDetails user
     */
    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.findByEmail(username);
        return new UserAwareUserDetails(user);
    }
}

