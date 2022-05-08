package com.example.bugtracker.service;

import com.example.bugtracker.dto.IdNameDTO;
import com.example.bugtracker.enums.SearchDataType;
import com.example.bugtracker.specification.criteria.SearchCriteria;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import tech.jhipster.service.QueryService;

import java.util.List;

@RequiredArgsConstructor
public abstract class SearchService<T> extends QueryService<T> {
    private final JpaSpecificationExecutor<T> repository;

    public List<? extends IdNameDTO> search(SearchCriteria criteria, Sort sort) {
        Specification<T> specification = createSpecification(criteria);
        List<T> items = repository.findAll(specification, sort);
        return transform(items);
    }

    protected abstract List<? extends IdNameDTO> transform(List<T> items);
    protected abstract Specification<T> createSpecification(SearchCriteria criteria);
    public abstract SearchDataType getDataType();
}
