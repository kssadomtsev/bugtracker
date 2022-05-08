package com.example.bugtracker.service.impl.filter;

import com.example.bugtracker.dto.user.UserDto;
import com.example.bugtracker.mapping.MappingService;
import com.example.bugtracker.model.Company_;
import com.example.bugtracker.model.Role_;
import com.example.bugtracker.model.User;
import com.example.bugtracker.model.User_;
import com.example.bugtracker.repository.UserRepository;
import com.example.bugtracker.specification.criteria.UserCriteria;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import tech.jhipster.service.QueryService;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class UserFilterService extends QueryService<User> {
    private final MappingService mapping;
    private final UserRepository repository;

    public Page<UserDto> getUserListSortedAndPageable(Pageable pageable, UserCriteria criteria) {
        return mapping.mapPages(repository.findAll(createSpecification(criteria), pageable), UserDto.class);
    }

    private Specification<User> createSpecification(UserCriteria criteria) {
        Specification<User> specification = Specification.where(null);
        if (Objects.nonNull(criteria)) {
            if (Objects.nonNull(criteria.getSearch())) {
                Specification<User> orSpec = buildStringSpecification(criteria.getSearch(), User_.email);
                orSpec = orSpec.or(buildStringSpecification(criteria.getSearch(), User_.firstName));
                orSpec = orSpec.or(buildStringSpecification(criteria.getSearch(), User_.lastName));
                specification = specification.and(orSpec);
            }

            if (Objects.nonNull(criteria.getRole())) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getRole()
                        , User_.roles, Role_.name));
            }

            if (Objects.nonNull(criteria.getCompany())) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getCompany(),
                        User_.company, Company_.id));
            }
        }
        return specification;
    }
}
