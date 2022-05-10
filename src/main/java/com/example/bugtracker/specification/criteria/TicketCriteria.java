package com.example.bugtracker.specification.criteria;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import tech.jhipster.service.filter.LongFilter;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TicketCriteria {
    private LongFilter author;
}
