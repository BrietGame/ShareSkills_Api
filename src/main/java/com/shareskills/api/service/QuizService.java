package com.shareskills.api.service;

import com.shareskills.api.model.Quiz;
import com.shareskills.api.repository.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuizService {

    @Autowired
    private QuizRepository quizRepository;

    public Optional<Quiz> findById(Long id) {
        return quizRepository.findById(id);
    }

    public List<Quiz> findAll() {
        return (List<Quiz>) quizRepository.findAll();
    }
}
