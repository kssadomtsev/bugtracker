package com.example.bugtracker.service.impl;

import com.example.bugtracker.dto.IdNameDTO;
import com.example.bugtracker.dto.company.CompanyDto;
import com.example.bugtracker.enums.SearchDataType;
import com.example.bugtracker.model.Company;
import com.example.bugtracker.model.Company_;
import com.example.bugtracker.repository.CompanyRepository;
import com.example.bugtracker.service.SearchService;
import com.example.bugtracker.specification.criteria.SearchCriteria;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class SearchCompanyServiceImpl extends SearchService<Company> {

    public SearchCompanyServiceImpl(CompanyRepository repository) {
        super(repository);
    }

    @Override
    protected List<CompanyDto> transform(List<Company> items) {
        return items.stream()
                .map(i -> CompanyDto.builder()
                        .id(i.getId())
                        .name(i.getName())
                        .address(i.getAddress()).build())
                .collect(Collectors.toList());
    }

    @Override
    protected Specification<Company> createSpecification(SearchCriteria criteria) {
        Specification<Company> specification = Specification.where(null);
        if (Objects.nonNull(criteria)) {
            if (Objects.nonNull(criteria.getSearch())) {
                Specification<Company> orSpec = buildStringSpecification(criteria.getSearch(), Company_.name);
                specification = specification.and(orSpec);
            }
        }
        return specification;
    }

    @Override
    public SearchDataType getDataType() {
        return SearchDataType.COMPANIES;
    }
}
