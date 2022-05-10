package com.example.bugtracker.service;

import com.example.bugtracker.model.Comment;
import org.springframework.stereotype.Service;


@Service
public interface CommentService {
    Comment save(Comment comment);
}
