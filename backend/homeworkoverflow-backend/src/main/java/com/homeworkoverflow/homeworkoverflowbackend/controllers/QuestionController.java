package com.homeworkoverflow.homeworkoverflowbackend.controllers;

import org.apache.tomcat.util.http.parser.HttpParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.homeworkoverflow.homeworkoverflowbackend.models.Answer;
import com.homeworkoverflow.homeworkoverflowbackend.models.Question;
import com.homeworkoverflow.homeworkoverflowbackend.repositories.QuestionRepository;

@Component
public class QuestionController {

    private ObjectMapper objectMapper;
    private HttpHeaders responseHeaders;
    private QuestionRepository questionRepository;

    public QuestionController(ObjectMapper objectMapper, QuestionRepository questionRepository) {
        this.objectMapper = objectMapper;
        this.responseHeaders = new HttpHeaders();
        this.questionRepository = questionRepository;

        this.responseHeaders.setContentType(MediaType.APPLICATION_JSON);
    }
    
    public ResponseEntity<String> retrieveQuestionsById(Integer questionId) throws JsonProcessingException {
        // List<String> tagsList = new ArrayList<>();
        // tagsList.add("test");
        // tagsList.add("test");

        Question question = questionRepository.findId(questionId);

        String idQuestionAsString = this.objectMapper.writeValueAsString(question);

        return new ResponseEntity<String>(idQuestionAsString, this.responseHeaders, HttpStatus.FOUND);
    }

    public ResponseEntity<String> retrieveQuestionforHome() {

        List<Question> questions = new ArrayList<Question>();
        questions = questionRepository.findHome();


        // List<String> tagsList = new ArrayList<>();
        // tagsList.add("test");
        // tagsList.add("test");

        // for(Integer x = 0; x < 10; x++) {
        //     Question question = new Question(x + 1, "title", "content", "username", tagsList, new Date(), 5, 10);

        //     try {
        //         String questionString = this.objectMapper.writeValueAsString(question);
        //         questions.add(questionString);
        //     } catch(JsonProcessingException e) {
        //         System.out.println("Encountered JSONProcessingException: retrieveQuestionforHome");
        //     }           
        // }

        List<String> lsQuestionString = new ArrayList<>();

        for(Iterator<Question> iterator = questions.iterator(); iterator.hasNext();) {
            lsQuestionString.add(iterator.next().toString());
        }

        // questions = questionRepository.findHome();
        // for(Iterator<Question> iterator = questions.iterator(); iterator.hasNext();) {
        //     lsQuestionString.add(iterator.next().toString());
        // }

        return new ResponseEntity<String>(lsQuestionString.toString(), this.responseHeaders, HttpStatus.FOUND);
    }

    public ResponseEntity<String> retrieveQuestionsAnsweredByUsers(String username) {
        List<String> questionsByUsers = new ArrayList<String>();

        List<String> tagsList = new ArrayList<>();
        tagsList.add("test");
        tagsList.add("test");

        for(Integer x = 0; x < 10; x++) {
            Question question = new Question(x + 1, "title", "content", "username", tagsList, new Date(), 5, 10);

            try {
                String questionString = this.objectMapper.writeValueAsString(question);
                questionsByUsers.add(questionString);
            } catch(JsonProcessingException e) {
                System.out.println("Encountered JSONProcessingException: retrieveQuestionforHome");
            }           
        }

        return new ResponseEntity<String>(questionsByUsers.toString(), this.responseHeaders, HttpStatus.FOUND);
    }

    public ResponseEntity<String> retrieveAnswersById(Integer questionId) {
        List<String> answersForQuestionId = new ArrayList<String>();
        
        for(int x = 0; x < 10; x++) {
            Answer answer = new Answer(5, 1,"<p>Test Answer</p>","asker_username", new Date(), 200, 300);

            try {
                String answerString = this.objectMapper.writeValueAsString(answer);
                answersForQuestionId.add(answerString);
            } catch(JsonProcessingException e) {
                System.out.println("Encountered JSONProcessingException: retrieveAnswersbyId");
            }
        }

        return new ResponseEntity<String>(answersForQuestionId.toString(), this.responseHeaders, HttpStatus.FOUND);
    }

    public void submitQuestion(Map<String, Object> questionDesc) {

    }

    public void upvoteQuestion(Integer questionId, String jwtToken) {


    }

    public void downvoteQuestion(Integer questionId, String jwtToken) {

    }
}
