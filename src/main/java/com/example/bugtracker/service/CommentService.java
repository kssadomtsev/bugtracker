package com.example.bugtracker.service;

import com.example.bugtracker.model.Comment;
import org.springframework.stereotype.Service;

/**
 * Service provides operations on ticket's comments
 */

public interface CommentService {
    /**
     * Save new comment
     *
     * @param comment comment
     * @return saved comment
     */
    Comment save(Comment comment);
}
