package com.example.bugtracker.service.impl;

import com.example.bugtracker.dto.IdNameDTO;
import com.example.bugtracker.enums.SearchDataType;
import com.example.bugtracker.model.Role;
import com.example.bugtracker.model.Role_;
import com.example.bugtracker.repository.RoleRepository;
import com.example.bugtracker.service.SearchService;
import com.example.bugtracker.specification.criteria.SearchCriteria;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * SearchService interface implementation for Role entity
 */

@Service
public class SearchRoleServiceImpl extends SearchService<Role> {

    public SearchRoleServiceImpl(RoleRepository repository) {
        super(repository);
    }

    @Override
    protected List<IdNameDTO> transform(List<Role> items) {
        return items.stream()
                .map(i -> IdNameDTO.builder()
                        .id(i.getId())
                        .name(i.getName()).build())
                .collect(Collectors.toList());
    }

    @Override
    protected Specification<Role> createSpecification(SearchCriteria criteria) {
        Specification<Role> specification = Specification.where(null);
        if (Objects.nonNull(criteria)) {
            if (Objects.nonNull(criteria.getSearch())) {
                Specification<Role> orSpec = buildStringSpecification(criteria.getSearch(), Role_.name);
                specification = specification.and(orSpec);
            }
        }
        return specification;
    }

    @Override
    public SearchDataType getDataType() {
        return SearchDataType.ROLES;
    }
}
