package com.raqami.universe.Quiz_MicroTwo.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuestionWrapper {
    private Long id;
    private String question;
    private String option1;
    private String option2;
    private String option3;
    private String option4;
}
