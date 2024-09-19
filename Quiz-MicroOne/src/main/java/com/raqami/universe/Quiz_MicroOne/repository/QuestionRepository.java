package com.raqami.universe.Quiz_MicroOne.repository;

import com.raqami.universe.Quiz_MicroOne.Model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question,Long> {
    List<Question> findByCategory(String category);

    @Query(value = "SELECT q.id FROM question q where q.category=:category ORDER BY RANDOM() LIMIT :num",nativeQuery = true)
    List<Long> findRandomQuestionByCategory(String category, Integer num);
}
