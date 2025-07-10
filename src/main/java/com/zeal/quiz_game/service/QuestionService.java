package com.zeal.quiz_game.service;

import com.zeal.quiz_game.model.Question;
import com.zeal.quiz_game.repo.QuestionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {

    @Autowired
    private QuestionRepo repo;

    public ResponseEntity<List<Question>> allQuestion() {
        try{
            return new ResponseEntity<>(repo.findAll(),HttpStatus.OK);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(),HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<Question> getQuestionById(int id){
        try {
            Question question = repo.findById(id).orElse(new Question());
            return new ResponseEntity<>(question, HttpStatus.OK);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(new Question(),HttpStatus.BAD_REQUEST);
    }


    public ResponseEntity<String> addQuestion(Question question) {
        try {
            repo.save(question);
            return new ResponseEntity<>("Added Successfully",HttpStatus.CREATED);
        }
        catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>("Failed to added",HttpStatus.BAD_REQUEST);
        }

    }

    public ResponseEntity<String> updateQuestion(int id,Question question){
        try {
            if (!repo.existsById(id)) {
                return new ResponseEntity<>("ID not found", HttpStatus.NOT_FOUND);
            }

            return repo.findById(id).map(item ->
            {
                item.setQuestion(question.getQuestion());
                item.setOption1(question.getOption1());
                item.setOption2(question.getOption2());
                item.setOption3(question.getOption3());
                item.setOption4(question.getOption4());
                item.setCorrectAnswer(question.getCorrectAnswer());
                item.setCategory(question.getCategory());
                item.setDifficultyLevel(question.getDifficultyLevel());
                repo.save(item);
                return new ResponseEntity<>("Updated Successfully", HttpStatus.OK);
            }).orElse(new ResponseEntity<>("Question not Found", HttpStatus.NOT_FOUND));
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>("Failed to Update",HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<String> deletedQuestion(int id) {
        try {
            if (!repo.existsById(id)){
                return new ResponseEntity<>("ID Not Found",HttpStatus.NOT_FOUND);
            }
            repo.deleteById(id);
            return new ResponseEntity<>("Deleted",HttpStatus.OK);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>("Failed to delete",HttpStatus.BAD_REQUEST);

    }

    public ResponseEntity<List<Question>> getQuestionByCategory(String category) {
        try {
            List<Question> questions =repo.findByCategory(category);
            return new ResponseEntity<>(questions,HttpStatus.FOUND);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(),HttpStatus.BAD_REQUEST);

    }
}
