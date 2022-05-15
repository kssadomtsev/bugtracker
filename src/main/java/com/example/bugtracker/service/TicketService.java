package com.example.bugtracker.service;

import com.example.bugtracker.model.Ticket;
import org.springframework.stereotype.Service;

/**
 * Service provides operations on ticket
 */
public interface TicketService {
    /**
     * Save ticket
     *
     * @param ticket
     * @return saved ticket
     */

    Ticket save(Ticket ticket);
    /**
     * Find ticket by id
     *
     * @param id ticket's id
     * @return Ticket
     */

    Ticket findById(Long id);
    /**
     * Delete ticket by id
     *
     * @param id ticket's id
     */

    void deleteById(Long id);
}
