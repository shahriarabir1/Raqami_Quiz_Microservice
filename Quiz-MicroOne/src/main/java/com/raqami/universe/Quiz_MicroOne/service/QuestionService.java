package com.raqami.universe.Quiz_MicroOne.service;


import com.raqami.universe.Quiz_MicroOne.Model.AnswerResponse;
import com.raqami.universe.Quiz_MicroOne.Model.Question;
import com.raqami.universe.Quiz_MicroOne.Model.QuestionWrapper;
import com.raqami.universe.Quiz_MicroOne.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class QuestionService {
    private final QuestionRepository questionRepository;
    public ResponseEntity<List<Question>> findAll() throws Exception {
        try {
            return new ResponseEntity<>(questionRepository.findAll(),HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<List<Question>> findByCategory(String category) throws Exception {
        try {
            return new ResponseEntity<>(questionRepository.findByCategory(category),HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }

    public Question addQuestion(Question question) {
        return questionRepository.save(question);
    }

    public List<Question> addAllQuestion(List<Question> question) {
        List<Question> questions=new ArrayList<>();
        for (Question q : question) {
            Question ques=questionRepository.save(q);
            questions.add(ques);
        }
        return questions;
    }

    public ResponseEntity<List<Long>> getQuestionForQuiz(String category, int num) {
        List<Long>questions=questionRepository.findRandomQuestionByCategory(category,num);
        return new ResponseEntity<>(questions,HttpStatus.OK);
    }

    public ResponseEntity<List<QuestionWrapper>> getQuestionFromId(List<Long> questionId) {
       List<QuestionWrapper>questionWrappers=new ArrayList<>();
       List<Question> questions=new ArrayList<>();
       for(Long id:questionId){
          questions.add(questionRepository.findById(id).get());
       }
       for(Question question:questions){
           QuestionWrapper questionWrapper=new QuestionWrapper(question.getId(),question.getQuestion(),question.getOption1(),question.getOption2(),question.getOption3(),question.getOption4());
           questionWrappers.add(questionWrapper);
       }
       return new ResponseEntity<>(questionWrappers,HttpStatus.OK);
    }

    public ResponseEntity<Integer> getScore(List<AnswerResponse> answers) {
        int right=0;
        for (AnswerResponse answer : answers) {
            Question question = questionRepository.findById(answer.getQuestionId()).get();
            if(answer.getAnswer().equals(question.getAnswer())){
                right++;
            }

        }
        return new ResponseEntity<>(right,HttpStatus.OK);
    }
}
