package com.example.bugtracker.controller;

import com.example.bugtracker.dto.IdNameDTO;
import com.example.bugtracker.enums.SearchDataType;
import com.example.bugtracker.service.SearchService;
import com.example.bugtracker.specification.criteria.SearchCriteria;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class SearchController {
    private final Map<SearchDataType, SearchService<?>> serviceMap;

    @Operation(summary = "Retry list of sorted searchable values")
    @GetMapping("/{dataType}/search/list")
    public List<? extends IdNameDTO> search(Sort sort, @PathVariable String dataType, SearchCriteria criteria) {
        return serviceMap.get(SearchDataType.valueOf(dataType.toUpperCase()))
                .search(criteria, sort);
    }
}
