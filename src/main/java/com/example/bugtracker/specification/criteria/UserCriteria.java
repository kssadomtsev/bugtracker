package com.example.bugtracker.specification.criteria;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import tech.jhipster.service.filter.Filter;
import tech.jhipster.service.filter.LongFilter;
import tech.jhipster.service.filter.StringFilter;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserCriteria {
    private StringFilter search;
    private Filter<String> role;
    private LongFilter company;
}
