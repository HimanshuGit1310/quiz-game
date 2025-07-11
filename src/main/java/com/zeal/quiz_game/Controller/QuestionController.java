package com.zeal.quiz_game.Controller;

import com.zeal.quiz_game.model.Question;
import com.zeal.quiz_game.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/question")
public class QuestionController {

    @Autowired
    private QuestionService service;

    @GetMapping("/allQuestion")
    public ResponseEntity<List<Question>> allQuestion(){
            return service.allQuestion();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Question> getQuestionById(@PathVariable int id) {
        return service.getQuestionById(id);
    }

    @PostMapping("/addQuestion")
    public ResponseEntity<String> addQuestion(@RequestBody Question question){
            return service.addQuestion(question);
        }


    @PutMapping("/updateQuestion/{id}")
    public ResponseEntity<String> updateQuestion(@PathVariable int id,@RequestBody Question question){
            return service.updateQuestion(id, question);
        }

    @DeleteMapping("/deleteQuestion/{id}")
    public  ResponseEntity<String> deleteQuestion(@PathVariable int id) {
            return service.deletedQuestion(id);
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<List<Question>> getQuestionByCategory(@RequestParam String category){
        return service.getQuestionByCategory(category);
    }
}


