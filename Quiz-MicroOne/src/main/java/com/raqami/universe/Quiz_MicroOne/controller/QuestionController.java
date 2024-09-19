package com.raqami.universe.Quiz_MicroOne.controller;


import com.raqami.universe.Quiz_MicroOne.Model.AnswerResponse;
import com.raqami.universe.Quiz_MicroOne.Model.Question;
import com.raqami.universe.Quiz_MicroOne.Model.QuestionWrapper;
import com.raqami.universe.Quiz_MicroOne.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("question")
@RequiredArgsConstructor
public class QuestionController {
    private final QuestionService questionService;
    @GetMapping("getAll")
    public ResponseEntity<List<Question>> getAllQuestions() throws Exception {
            return questionService.findAll();

    }

    @GetMapping("category/{category}")
    public ResponseEntity<List<Question>> getAllQuestionsByCategory(@PathVariable String category) throws Exception {

            return  questionService.findByCategory(category);


    }

    @PostMapping("add")
    public ResponseEntity<Question> addQuestion(@RequestBody Question question) {
        return ResponseEntity.ok().body(questionService.addQuestion(question));
    }
    @PostMapping("add/all")
    public ResponseEntity<List<Question>> addAllQuestion(@RequestBody List<Question> question) {
        return ResponseEntity.ok().body(questionService.addAllQuestion(question));
    }

    @GetMapping("generate")
    public ResponseEntity<List<Long>> generateQuestion(@RequestParam String category, @RequestParam int num) {
        return questionService.getQuestionForQuiz(category,num);

    }

    @PostMapping("getQuestion")
    public ResponseEntity<List<QuestionWrapper>> getQuestion(@RequestBody List<Long> questionId) {
        return questionService.getQuestionFromId(questionId);

    }

    @PostMapping("getScore")
    public ResponseEntity<Integer> getScore(@RequestBody List<AnswerResponse> answers) {
        return questionService.getScore(answers);
    }

}
