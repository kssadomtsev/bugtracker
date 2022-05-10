package com.example.bugtracker.controller;

import com.example.bugtracker.dto.ticket.*;
import com.example.bugtracker.dto.user.UserDto;
import com.example.bugtracker.service.impl.TicketProcessorService;
import com.example.bugtracker.specification.criteria.TicketCriteria;
import com.example.bugtracker.specification.criteria.UserCriteria;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping("/tickets")
@AllArgsConstructor
public class TicketController {
    private final TicketProcessorService service;

    @GetMapping
    public Page<TicketDto> getTicketList(@PageableDefault(sort = "id", direction = Sort.Direction.ASC) Pageable pageable,
                                     TicketCriteria criteria) {
        return service.getTicketListSortedAndPageable(pageable, criteria);
    }

    @GetMapping("/{id}")
    public TicketDto getTicket(@PathVariable Long id) {
        return service.getDtoById(id);
    }


    @PostMapping
    public TicketDto create(@Valid @RequestBody TicketCreateDto ticketCreateDto) {
        return service.create(ticketCreateDto);
    }

    @PostMapping("/{id}/comment")
    public TicketDto addComment(@PathVariable Long id, @Valid @RequestBody CommentCreateDto commentCreateDto) {
        return service.addComment(id, commentCreateDto);
    }

    @DeleteMapping("/{id}")
    public void deleteTicket(@PathVariable Long id) {
        service.delete(id);
    }

    @PostMapping("/{id}/assign")
    public TicketDto assign(@PathVariable Long id, @Valid @RequestBody TicketAssignDto ticketAssignDto) {
        return service.assign(id, ticketAssignDto);
    }

    @PostMapping("/{id}/solve")
    public TicketDto assign(@PathVariable Long id, @Valid @RequestBody CommentCreateDto commentCreateDto) {
        return service.solve(id, commentCreateDto);
    }

    @PostMapping("/{id}/verify")
    public TicketDto verify(@PathVariable Long id, @Valid @RequestBody TicketVerifyDto ticketVerifyDto) {
        return service.verify(id, ticketVerifyDto);
    }

    @PostMapping("/{id}/reopen")
    public TicketDto reopen(@PathVariable Long id, @Valid @RequestBody CommentCreateDto commentCreateDto) {
        return service.reopen(id, commentCreateDto);
    }
}
