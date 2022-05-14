package com.example.bugtracker.controller;

import com.example.bugtracker.dto.ticket.*;
import com.example.bugtracker.service.impl.TicketProcessorService;
import com.example.bugtracker.specification.criteria.TicketCriteria;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping("/tickets")
@AllArgsConstructor
public class TicketController {
    private final TicketProcessorService service;

    @Operation(summary = "Get filtered, pageable and sorted list of tickets")
    @GetMapping
    public Page<TicketDto> getTicketList(@PageableDefault(sort = "id", direction = Sort.Direction.ASC) Pageable pageable,
                                     TicketCriteria criteria) {
        return service.getTicketListSortedAndPageable(pageable, criteria);
    }

    @Operation(summary = "Get ticket by id")
    @GetMapping("/{id}")
    public TicketDto getTicket(@PathVariable Long id) {
        return service.getDtoById(id);
    }

    @Operation(summary = "Create new ticket")
    @PostMapping
    public TicketDto create(@Valid @RequestBody TicketCreateDto ticketCreateDto) {
        return service.create(ticketCreateDto);
    }

    @Operation(summary = "Add comment to ticket")
    @PostMapping("/{id}/comment")
    public TicketDto addComment(@PathVariable Long id, @Valid @RequestBody CommentCreateDto commentCreateDto) {
        return service.addComment(id, commentCreateDto);
    }

    @Operation(summary = "Delete ticket by id")
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public void deleteTicket(@PathVariable Long id) {
        service.delete(id);
    }

    @Operation(summary = "Assign ticket to responsible by id")
    @PostMapping("/{id}/assign")
    @PreAuthorize("hasAnyAuthority('ADMIN','DEVELOPER')")
    public TicketDto assign(@PathVariable Long id, @Valid @RequestBody TicketAssignDto ticketAssignDto) {
        return service.assign(id, ticketAssignDto);
    }

    @Operation(summary = "Solve ticket by id")
    @PostMapping("/{id}/solve")
    @PreAuthorize("hasAnyAuthority('ADMIN','DEVELOPER')")
    public TicketDto solve(@PathVariable Long id, @Valid @RequestBody CommentCreateDto commentCreateDto) {
        return service.solve(id, commentCreateDto);
    }

    @Operation(summary = "Verify ticket by id")
    @PostMapping("/{id}/verify")
    @PreAuthorize("hasAnyAuthority('ADMIN','QA')")
    public TicketDto verify(@PathVariable Long id, @Valid @RequestBody TicketVerifyDto ticketVerifyDto) {
        return service.verify(id, ticketVerifyDto);
    }

    @Operation(summary = "Reopen ticket by id")
    @PostMapping("/{id}/reopen")
    public TicketDto reopen(@PathVariable Long id, @Valid @RequestBody CommentCreateDto commentCreateDto) {
        return service.reopen(id, commentCreateDto);
    }
}
