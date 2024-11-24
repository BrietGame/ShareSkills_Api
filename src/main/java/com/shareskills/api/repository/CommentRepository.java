package com.shareskills.api.repository;

import com.shareskills.api.model.Comment;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CommentRepository extends CrudRepository<Comment, Long> {
    List<Comment> findByFormationId(Long formationId);
}
