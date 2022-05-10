package com.example.bugtracker.service.impl;

import com.example.bugtracker.dto.ticket.*;
import com.example.bugtracker.enums.StatusTicket;
import com.example.bugtracker.mapping.MappingService;
import com.example.bugtracker.model.Comment;
import com.example.bugtracker.model.Ticket;
import com.example.bugtracker.model.User;
import com.example.bugtracker.service.CommentService;
import com.example.bugtracker.service.TicketService;
import com.example.bugtracker.service.UserService;
import com.example.bugtracker.service.impl.filter.TicketFilterService;
import com.example.bugtracker.specification.criteria.TicketCriteria;
import com.example.bugtracker.utils.TicketUtil;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Set;

@Service
@AllArgsConstructor
public class TicketProcessorService {
    private final UserService userService;
    private final TicketService ticketService;
    private final CommentService commentService;
    private final MappingService mappingService;
    private final TicketFilterService filterService;

    private static final Set<String> DEVELOPER_ROLE_SET = Set.of("DEVELOPER");
    private static final Set<StatusTicket> TICKET_STATUSES_FOR_ASSIGNMENT = Set.of(StatusTicket.NEW, StatusTicket.REOPEN);
    private static final Set<StatusTicket> TICKET_STATUSES_FOR_SOLVE = Set.of(StatusTicket.ASSIGNED);
    private static final Set<StatusTicket> TICKET_STATUSES_FOR_VERIFY = Set.of(StatusTicket.SOLVED);
    private static final Set<StatusTicket> TICKET_STATUSES_FOR_REOPEN = Set.of(StatusTicket.CLOSED);


    @Transactional
    public TicketDto create(TicketCreateDto ticketCreateDto) {
        Ticket ticket = mappingService.map(ticketCreateDto, Ticket.class);
        ticket.setStatus(StatusTicket.NEW);
        return mappingService.map(ticketService.save(ticket), TicketDto.class);
    }

    @Transactional
    public TicketDto addComment(Long id, CommentCreateDto commentCreateDto) {
        Comment comment = mappingService.map(commentCreateDto, Comment.class);
        comment.setTicket(ticketService.findById(id));
        commentService.save(comment);
        return mappingService.map(ticketService.findById(id), TicketDto.class);
    }

    @Transactional
    public void delete(Long id) {
        ticketService.deleteById(id);
    }

    @Transactional
    public TicketDto assign(Long id, TicketAssignDto ticketAssignDto) {
        Ticket ticket = ticketService.findById(id);
        TicketUtil.checkCorrectTicketStatus(ticket.getStatus(), TICKET_STATUSES_FOR_ASSIGNMENT);

        User user = userService.findByEmail(ticketAssignDto.getResponsible());
        TicketUtil.checkCorrectRole(user, DEVELOPER_ROLE_SET);

        Comment comment = mappingService.map(ticketAssignDto, Comment.class);
        comment.setTicket(ticket);
        commentService.save(comment);

        ticket.setResponsible(user);
        ticket.setStatus(StatusTicket.ASSIGNED);

        return mappingService.map(ticketService.save(ticket), TicketDto.class);
    }

    @Transactional
    public TicketDto solve(Long id, CommentCreateDto commentCreateDto) {
        Ticket ticket = ticketService.findById(id);
        TicketUtil.checkCorrectTicketStatus(ticket.getStatus(), TICKET_STATUSES_FOR_SOLVE);

        Comment comment = mappingService.map(commentCreateDto, Comment.class);
        comment.setTicket(ticket);
        commentService.save(comment);

        ticket.setStatus(StatusTicket.SOLVED);

        return mappingService.map(ticketService.save(ticket), TicketDto.class);
    }

    @Transactional
    public TicketDto verify(Long id, TicketVerifyDto ticketVerifyDto) {
        Ticket ticket = ticketService.findById(id);
        TicketUtil.checkCorrectTicketStatus(ticket.getStatus(), TICKET_STATUSES_FOR_VERIFY);

        Comment comment = mappingService.map(ticketVerifyDto, Comment.class);
        comment.setTicket(ticket);
        commentService.save(comment);

        if (ticketVerifyDto.isCorrect()) {
            ticket.setStatus(StatusTicket.CLOSED);
            ticket.setSolvedAt(LocalDateTime.now());
        } else {
            ticket.setStatus(StatusTicket.ASSIGNED);
        }

        return mappingService.map(ticketService.save(ticket), TicketDto.class);
    }

    @Transactional
    public TicketDto reopen(Long id, CommentCreateDto commentCreateDto) {
        Ticket ticket = ticketService.findById(id);
        TicketUtil.checkCorrectTicketStatus(ticket.getStatus(), TICKET_STATUSES_FOR_REOPEN);

        Comment comment = mappingService.map(commentCreateDto, Comment.class);
        comment.setTicket(ticket);
        commentService.save(comment);

        ticket.setStatus(StatusTicket.ASSIGNED);

        return mappingService.map(ticketService.save(ticket), TicketDto.class);
    }

    public Page<TicketDto> getTicketListSortedAndPageable(Pageable pageable, TicketCriteria criteria) {
        return filterService.getTicketListSortedAndPageable(pageable, criteria);
    }

    public TicketDto getDtoById(Long id) {
        return mappingService.map(ticketService.findById(id), TicketDto.class);
    }
}
