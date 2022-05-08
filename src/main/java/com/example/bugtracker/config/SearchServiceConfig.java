package com.example.bugtracker.config;

import com.example.bugtracker.enums.SearchDataType;
import com.example.bugtracker.service.SearchService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Configuration
public class SearchServiceConfig {
    @Bean
    public Map<SearchDataType, SearchService<?>> createSearchServiceMap(List<SearchService<?>> serviceList) {
        return serviceList.stream()
                .collect(Collectors.toMap(SearchService::getDataType, Function.identity()));

    }
}
