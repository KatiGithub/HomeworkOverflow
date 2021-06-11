package com.homeworkoverflow.homeworkoverflowbackend.controllers;

import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.homeworkoverflow.homeworkoverflowbackend.models.Answer;
import com.homeworkoverflow.homeworkoverflowbackend.models.Question;
import com.homeworkoverflow.homeworkoverflowbackend.models.User;
import com.homeworkoverflow.homeworkoverflowbackend.repositories.AuthRepository;
import com.homeworkoverflow.homeworkoverflowbackend.repositories.QuestionRepository;
import com.homeworkoverflow.homeworkoverflowbackend.utils.FirebaseAdmin;

@Component
public class QuestionController {

    private ObjectMapper objectMapper;
    private HttpHeaders responseHeaders;
    private QuestionRepository questionRepository;
    private FirebaseAdmin firebaseAdmin;
    private AuthRepository authRepository;

    public QuestionController(ObjectMapper objectMapper, QuestionRepository questionRepository, FirebaseAdmin firebaseAdmin, AuthRepository authRepository) {
        this.objectMapper = objectMapper;
        this.responseHeaders = new HttpHeaders();
        this.questionRepository = questionRepository;
        this.firebaseAdmin = firebaseAdmin;
        this.authRepository = authRepository;

        this.responseHeaders.setContentType(MediaType.APPLICATION_JSON);
    }

    public ResponseEntity<String> retrieveQuestionsById(Integer questionId) throws JsonProcessingException {
        try {
            Question question = questionRepository.findId(questionId);

            String idQuestionAsString = this.objectMapper.writeValueAsString(question);

            return new ResponseEntity<String>(idQuestionAsString, this.responseHeaders, HttpStatus.FOUND);
        } catch(DataAccessException ex) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<String> retrieveQuestionforHome() {

        try {
            List<Question> questions = new ArrayList<Question>();
            questions = questionRepository.findHome();
            List<String> lsQuestionString = new ArrayList<>();

            for (Iterator<Question> iterator = questions.iterator(); iterator.hasNext();) {
                lsQuestionString.add(iterator.next().toString());
            }
            return new ResponseEntity<String>(lsQuestionString.toString(), this.responseHeaders, HttpStatus.FOUND);
        } catch (DataAccessException ex) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<String> retrieveQuestionsAnsweredByUser(Integer userid) {
        try {
            List<Answer> questionsByUsers = new ArrayList<Answer>();
            List<String> strQuestionsByUsers = new ArrayList<String>();
            questionsByUsers = questionRepository.findAnswersByUserId(Long.valueOf(userid));

            for (Iterator<Answer> iterator = questionsByUsers.iterator(); iterator.hasNext();) {
                strQuestionsByUsers.add(iterator.next().toString());
            }

            return new ResponseEntity<String>(strQuestionsByUsers.toString(), this.responseHeaders, HttpStatus.FOUND);
        } catch (DataAccessException ex) {
            ex.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<String> retrieveAnswersByAnswerId(Long answerId) {
        try {
            Answer answer = new Answer();
            answer = questionRepository.findAnswerbyAnswerId(answerId);

            return new ResponseEntity<String>(answer.toString(), this.responseHeaders, HttpStatus.FOUND);
        } catch (DataAccessException ex) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<String> retrieveAnswersByQuestionId(Long questionId) {
        try {
            List<Answer> lsAnswers = questionRepository.findAnswersByQuestionId(questionId);
            List<String> strlsAnswers = new ArrayList<String>();
            
            for(Iterator<Answer> iterator = lsAnswers.iterator(); iterator.hasNext();) {
                strlsAnswers.add(iterator.next().toString());
            }

            return new ResponseEntity<String>(strlsAnswers.toString(), this.responseHeaders, HttpStatus.FOUND);
        } catch (DataAccessException ex) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity submitQuestion(String questionDesc, String jwtToken) {

        try {
            Question question = this.objectMapper.readValue(questionDesc, Question.class);
            System.out.println(question.toString());

            User QuestionSubmitter = firebaseAdmin.getUser(jwtToken);
            Long idQuestionSubmitter = authRepository.getUserId(QuestionSubmitter.getEmail());


            question.setAskeruserid(idQuestionSubmitter);
            questionRepository.submitQuestion(question);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception ex) {
            ex.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity upvoteAnswer(Long answerid, String jwtToken) {
        try {
            User upvoter = firebaseAdmin.getUser(jwtToken);
            Long upvoterid = authRepository.getUserId(upvoter.getEmail());

            questionRepository.upvoteAnswer(answerid, upvoterid);

            return new ResponseEntity<>(HttpStatus.OK);
        } catch (DataAccessException ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
