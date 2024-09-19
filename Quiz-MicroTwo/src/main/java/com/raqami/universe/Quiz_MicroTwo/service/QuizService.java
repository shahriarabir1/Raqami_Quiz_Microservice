package com.raqami.universe.Quiz_MicroTwo.service;

import com.raqami.universe.Quiz_MicroTwo.Model.AnswerResponse;
import com.raqami.universe.Quiz_MicroTwo.Model.QuestionWrapper;
import com.raqami.universe.Quiz_MicroTwo.Model.Quiz;
import com.raqami.universe.Quiz_MicroTwo.feign.GetQuestion;
import com.raqami.universe.Quiz_MicroTwo.repository.QuizRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class QuizService {
    private final QuizRepository quizRepository;
    @Autowired
    GetQuestion getQuestion;

    public ResponseEntity<String> createQuiz(String category, int num, String title) {
        List<Long>questions= getQuestion.generateQuestion(category,num).getBody();
        Quiz quiz=new Quiz();
        quiz.setTitle(title);
        quiz.setQuestions(questions);
        quizRepository.save(quiz);
        return ResponseEntity.ok("Quiz created");
    }

    public ResponseEntity<List<QuestionWrapper>> getQuizQuestion(Long id) {
       Optional<Quiz> quiz= quizRepository.findById(id);
       List<Long>questions=quiz.get().getQuestions();
       ResponseEntity<List<QuestionWrapper>>ques=getQuestion.getQuestion(questions);

        return ques;
    }

    public ResponseEntity<Integer> calculateResult(Long id, List<AnswerResponse> answer) {
        Optional<Quiz> quiz=quizRepository.findById(id);
        ResponseEntity<Integer> score=getQuestion.getScore(answer);

        return score;
    }
}
