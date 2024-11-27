package com.shareskills.api.controller;

import com.shareskills.api.model.Formation;
import com.shareskills.api.model.Quiz;
import com.shareskills.api.model.dto.FormationDTO;
import com.shareskills.api.model.dto.QuizDTO;
import com.shareskills.api.response.ResponseJson;
import com.shareskills.api.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/admin/quiz")
public class AdminQuizController {

    @Autowired
    private QuizService quizService;

    @GetMapping("/list")
    public ResponseJson<List<Quiz>> getQuizzes() {
        return new ResponseJson<>(quizService.findAll(), HttpStatus.OK.value());
    }

    @GetMapping("/{id}")
    public ResponseJson<Optional<Quiz>> getQuizById(Long id) {
        return new ResponseJson<>(quizService.findById(id), HttpStatus.OK.value());
    }

    @PostMapping("/create")
    public ResponseJson<Quiz> createQuiz(QuizDTO quizDTO) {
        return new ResponseJson<>(quizService.createQuiz(quizDTO), HttpStatus.CREATED.value());
    }

    @PutMapping("/update/{id}")
    public ResponseJson<Quiz> updateFormation(@PathVariable Long id, @RequestBody QuizDTO quizDTO) {
        Optional<Quiz> formationOptional = quizService.findById(id);
        if (formationOptional.isPresent()) {
            quizDTO.setId(id);
            return new ResponseJson<>(quizService.updateQuiz(quizDTO), HttpStatus.OK.value());
        }
        return new ResponseJson<>(null, HttpStatus.NOT_FOUND.value());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseJson<Quiz> deleteFormation(@PathVariable Long id) {
        Optional<Quiz> formation = quizService.findById(id);
        if (formation.isPresent()) {
            quizService.deleteQuiz(formation.get());
            return new ResponseJson<>(null, HttpStatus.NO_CONTENT.value());
        }
        return new ResponseJson<>(null, HttpStatus.NOT_FOUND.value());
    }
}
