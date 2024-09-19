package com.raqami.universe.Quiz_MicroTwo.repository;

import com.raqami.universe.Quiz_MicroTwo.Model.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuizRepository extends JpaRepository<Quiz,Long> {
}
