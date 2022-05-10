package com.example.bugtracker.service.impl;

import com.example.bugtracker.exception.NotFoundException;
import com.example.bugtracker.model.Ticket;
import com.example.bugtracker.repository.TicketRepository;
import com.example.bugtracker.service.TicketService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TicketServiceImpl implements TicketService {
    private static final String TICKET_NOT_FOUND_BY_ID = "Ticket not found by id %d";

    private final TicketRepository repository;

    @Override
    public Ticket save(Ticket ticket) {
        return repository.save(ticket);
    }

    @Override
    public Ticket findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new NotFoundException(String.format(TICKET_NOT_FOUND_BY_ID, id)));
    }

    @Override
    public void deleteById(Long id) {
        if (!repository.existsById(id)) {
            throw new NotFoundException(String.format(TICKET_NOT_FOUND_BY_ID, id));
        } else {
            repository.deleteById(id);
        }
    }
}
