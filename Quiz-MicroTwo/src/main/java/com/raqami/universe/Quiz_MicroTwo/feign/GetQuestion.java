package com.raqami.universe.Quiz_MicroTwo.feign;
import com.raqami.universe.Quiz_MicroTwo.Model.AnswerResponse;
import com.raqami.universe.Quiz_MicroTwo.Model.QuestionWrapper;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient("QUIZ-SERVICE")
public interface GetQuestion {
    @GetMapping("question/generate")
    public ResponseEntity<List<Long>> generateQuestion(@RequestParam String category, @RequestParam int num);

    @PostMapping("question/getQuestion")
    public ResponseEntity<List<QuestionWrapper>> getQuestion(@RequestBody List<Long> questionId);


    @PostMapping("question/getScore")
    public ResponseEntity<Integer> getScore(@RequestBody List<AnswerResponse> answers);

}
