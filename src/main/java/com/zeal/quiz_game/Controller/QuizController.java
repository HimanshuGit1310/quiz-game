package com.zeal.quiz_game.Controller;

import com.zeal.quiz_game.model.Question;
import com.zeal.quiz_game.model.QuestionWrapper;
import com.zeal.quiz_game.model.Quiz;
import com.zeal.quiz_game.model.Response;
import com.zeal.quiz_game.repo.QuizRepo;
import com.zeal.quiz_game.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/quiz")
public class QuizController {

    @Autowired
    private QuizService quizService;

    @PostMapping("/create")
    public ResponseEntity<String> createQuiz(@RequestParam String category,@RequestParam int numQ,@RequestParam String title){
        return quizService.createQuiz(category,numQ,title);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<List<QuestionWrapper>> getQuizQuestion(@PathVariable Integer id){
        return quizService.getQuizQuestion(id);
    }

    @PostMapping("/submit/{id}")
    public ResponseEntity<Integer> submitQuiz(@PathVariable int id,@RequestBody List<Response> responses){
        return quizService.calculateResponse(id,responses);
    }

}
