package com.zeal.quiz_game.service;

import com.zeal.quiz_game.model.Question;
import com.zeal.quiz_game.model.QuestionWrapper;
import com.zeal.quiz_game.model.Quiz;
import com.zeal.quiz_game.model.Response;
import com.zeal.quiz_game.repo.QuestionRepo;
import com.zeal.quiz_game.repo.QuizRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuizService {

    @Autowired
    private QuizRepo quizRepo;

    @Autowired
    private QuestionRepo questionRepo;

    public ResponseEntity<String> createQuiz(String category, int numQ,String title) {
        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestions(questionRepo.findRandomQuestionByCategory(numQ,category));
        quizRepo.save(quiz);

        return new ResponseEntity<>("Success", HttpStatus.OK);
    }

    public ResponseEntity<List<QuestionWrapper>> getQuizQuestion(Integer id) {
        Optional<Quiz> quiz = quizRepo.findById(id);
        List<Question> questionsFromDB =  quiz.get().getQuestions(); //using optional we will fetch like that
        List<QuestionWrapper> questionForUser = new ArrayList<>();
        for (Question q : questionsFromDB){
                QuestionWrapper qw = new QuestionWrapper(q.getId(),q.getTitle(),q.getOption1(),q.getOption2(),q.getOption3(),q.getOption4());
                questionForUser.add(qw);
        }
        return new ResponseEntity<>(questionForUser,HttpStatus.OK);

    }

    public ResponseEntity<Integer> calculateResponse(int id, List<Response> responses) {
            Quiz quiz = quizRepo.findById(id).get();
            List<Question> questions = quiz.getQuestions();
            int right = 0;
            int i = 0;
            for (Response response : responses){
                if (response.getResponse().equals(questions.get(i).getCorrectAnswer()))
                    right++;
                i++;
            }
        return new ResponseEntity<>(right,HttpStatus.OK);

    }

}
