package com.example.bugtracker.config;

import com.example.bugtracker.audit.SpringSecurityUserAware;
import com.example.bugtracker.model.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@EnableJpaAuditing(auditorAwareRef="auditorProvider")
public class PersistenceConfig {
    @Bean
    public AuditorAware<User> auditorProvider() {
        return new SpringSecurityUserAware();
    }
}