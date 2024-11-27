package com.shareskills.api.service;

import com.shareskills.api.mapper.QuizMapper;
import com.shareskills.api.model.Quiz;
import com.shareskills.api.model.dto.QuizDTO;
import com.shareskills.api.repository.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuizService {

    @Autowired
    private QuizRepository quizRepository;

    @Autowired
    private QuizMapper quizMapper;

    public Optional<Quiz> findById(Long id) {
        return quizRepository.findById(id);
    }

    public List<Quiz> findAll() {
        return (List<Quiz>) quizRepository.findAll();
    }

    public Quiz createQuiz(QuizDTO quizDTO) {
        return quizRepository.save(quizMapper.toEntity(quizDTO));
    }

    public Quiz updateQuiz(QuizDTO quizDTO) {
        return quizRepository.save(quizMapper.toEntity(quizDTO));
    }

    public void deleteQuiz(Quiz quiz) {
        quizRepository.delete(quiz);
    }
}
