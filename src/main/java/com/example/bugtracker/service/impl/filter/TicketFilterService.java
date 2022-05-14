package com.example.bugtracker.service.impl.filter;

import com.example.bugtracker.dto.ticket.TicketDto;
import com.example.bugtracker.mapping.MappingService;
import com.example.bugtracker.model.*;
import com.example.bugtracker.repository.TicketRepository;
import com.example.bugtracker.specification.criteria.TicketCriteria;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import tech.jhipster.service.QueryService;

import java.util.Objects;

/**
 * QueryService interface implementation for Ticket entity
 */

@Service
@RequiredArgsConstructor
public class TicketFilterService extends QueryService<Ticket> {
    private final MappingService mapping;
    private final TicketRepository repository;

    /**
     * Get pageable list of tickets
     *
     * @param pageable page object
     * @param criteria filtered criteria
     * @return pageable list of ticket DTOs
     */
    public Page<TicketDto> getTicketListSortedAndPageable(Pageable pageable, TicketCriteria criteria) {
        return mapping.mapPages(repository.findAll(createSpecification(criteria), pageable), TicketDto.class);
    }

    /**
     * Build a specification
     *
     * @param criteria filtered criteria
     * @return specification
     */
    private Specification<Ticket> createSpecification(TicketCriteria criteria) {
        Specification<Ticket> specification = Specification.where(null);
        if (Objects.nonNull(criteria)) {
            if (Objects.nonNull(criteria.getAuthor())) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getAuthor()
                        , Ticket_.reporter, User_.id));
            }
        }
        return specification;
    }
}
