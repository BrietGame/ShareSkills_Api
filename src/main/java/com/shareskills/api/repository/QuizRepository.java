package com.shareskills.api.repository;

import com.shareskills.api.model.Quiz;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface QuizRepository extends CrudRepository<Quiz, Long> {
    List<Quiz> findByFormationId(Long formationId);
}
