package com.raqami.universe.Quiz_MicroTwo.controller;

import com.raqami.universe.Quiz_MicroTwo.Model.AnswerResponse;
import com.raqami.universe.Quiz_MicroTwo.Model.QuestionWrapper;
import com.raqami.universe.Quiz_MicroTwo.Model.QuizDto;
import com.raqami.universe.Quiz_MicroTwo.service.QuizService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("quiz")
@RequiredArgsConstructor
public class QuizController {
    private final QuizService quizService;
    @PostMapping("create")
    public ResponseEntity<String> createQuiz(@RequestBody QuizDto quizDto) {
    return quizService.createQuiz(quizDto.getCategoryName(),quizDto.getNumQuestions(),quizDto.getTitle());
    }

    @GetMapping("get/{id}")
    public ResponseEntity<List<QuestionWrapper>>getQuiz(@PathVariable Long id) {
        return quizService.getQuizQuestion(id);
    }

    @PostMapping("submit/{id}")
    public ResponseEntity<Integer> submitQuiz(@PathVariable Long id,@RequestBody List<AnswerResponse>answer) {
        return quizService.calculateResult(id,answer);
    }
}
