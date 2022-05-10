package com.example.bugtracker.audit;

import com.example.bugtracker.model.User;
import com.example.bugtracker.security.UserAwareUserDetails;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

public class SpringSecurityUserAware implements AuditorAware<User> {

    @Override
    public Optional<User> getCurrentAuditor() {
        User user = null;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof UserAwareUserDetails) {
            UserAwareUserDetails userPrincipal = (UserAwareUserDetails) authentication.getPrincipal();
            user = userPrincipal.getUser();
        }
        return Optional.of(user);
    }
}