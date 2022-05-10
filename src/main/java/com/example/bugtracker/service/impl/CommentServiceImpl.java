package com.example.bugtracker.service.impl;

import com.example.bugtracker.model.Comment;
import com.example.bugtracker.repository.CommentRepository;
import com.example.bugtracker.service.CommentService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CommentServiceImpl implements CommentService {
    private final CommentRepository repository;

    @Override
    public Comment save(Comment comment) {
        return repository.save(comment);
    }
}
