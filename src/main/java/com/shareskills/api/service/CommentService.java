package com.shareskills.api.service;

import com.shareskills.api.model.Comment;
import com.shareskills.api.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    public Optional<Comment> findById(Long id) {
        return commentRepository.findById(id);
    }

    public List<Comment> findAll() {
        return (List<Comment>) commentRepository.findAll();
    }
}
