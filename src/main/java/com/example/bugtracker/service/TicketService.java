package com.example.bugtracker.service;

import com.example.bugtracker.model.Ticket;
import org.springframework.stereotype.Service;


@Service
public interface TicketService {
    Ticket save(Ticket ticket);
    Ticket findById(Long id);
    void deleteById(Long id);
}
