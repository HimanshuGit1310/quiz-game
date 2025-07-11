package com.zeal.quiz_game.repo;

import com.zeal.quiz_game.model.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface QuizRepo extends JpaRepository<Quiz,Integer> {


}
