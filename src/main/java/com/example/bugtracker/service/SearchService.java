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

/**
 * Unified parametrized search service
 */

@RequiredArgsConstructor
public abstract class SearchService<T> extends QueryService<T> {
    private final JpaSpecificationExecutor<T> repository;

    /**
     * Get list of entities transformed to DTO
     *
     * @param criteria filter criteria
     * @param sort sorting order
     * @return list of DTO objects extended basic class
     */
    public List<? extends IdNameDTO> search(SearchCriteria criteria, Sort sort) {
        Specification<T> specification = createSpecification(criteria);
        List<T> items = repository.findAll(specification, sort);
        return transform(items);
    }

    /**
     * Transform list of entities into list of DTO
     *
     * @param items list of entities
     * @return list of DTOs
     */
    protected abstract List<? extends IdNameDTO> transform(List<T> items);

    /**
     * Create entity specification
     *
     * @param criteria criteria
     * @return specification
     */
    protected abstract Specification<T> createSpecification(SearchCriteria criteria);

    /**
     * Get data type of entity
     *
     * @return data type of entity
     */
    public abstract SearchDataType getDataType();
}
