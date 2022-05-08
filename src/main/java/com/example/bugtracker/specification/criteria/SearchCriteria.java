package com.example.bugtracker.specification.criteria;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import tech.jhipster.service.filter.StringFilter;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SearchCriteria {
    private StringFilter search;
}
