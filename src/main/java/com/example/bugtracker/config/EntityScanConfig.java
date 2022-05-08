package com.example.bugtracker.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@EntityScan("com.example.bugtracker.model")
public class EntityScanConfig {
}
