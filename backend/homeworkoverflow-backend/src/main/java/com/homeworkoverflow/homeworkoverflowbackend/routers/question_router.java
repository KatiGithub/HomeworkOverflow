package com.homeworkoverflow.homeworkoverflowbackend.routers;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.homeworkoverflow.homeworkoverflowbackend.controllers.QuestionController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class question_router {

    QuestionController qController;

    @Autowired
    public question_router() {
        this.qController = new QuestionController();
    }

    @RequestMapping(value = "/question/{id}", method = RequestMethod.GET)
    public ResponseEntity<String> retrieveQuestionsById(@PathVariable("id") Integer questionId)
            throws JsonProcessingException {

        return this.qController.retrieveQuestionsById(questionId);
    }

    @RequestMapping(value="/question/home", method = RequestMethod.GET)
    public ResponseEntity<String> retrieveQuestionforHome() {

        return this.qController.retrieveQuestionforHome();
    }

    @RequestMapping(value="/question/", method = RequestMethod.GET)
    public ResponseEntity<String> retrieveQuestionsAnsweredByUsers(@RequestParam String username) {

        return this.qController.retrieveQuestionsAnsweredByUsers(username);
    }

    @RequestMapping(value="/answer/{id}", method = RequestMethod.GET)
    public ResponseEntity<String> retrieveAnswersById(@PathVariable("id") Integer questionId) {

        return this.qController.retrieveAnswersById(questionId);
    }

    @RequestMapping(value="/addquestion/", method = RequestMethod.POST)
    public ResponseEntity submitQuestion(@RequestBody Map<String, Object> questionDesc) {

        this.qController.submitQuestion(questionDesc);

        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(value="/upvote/{id}", method = RequestMethod.GET)
    public ResponseEntity upvoteAnswer(@PathVariable("id") Integer questionId) {

        return new ResponseEntity(HttpStatus.OK);
    }
    
    @RequestMapping(value="/downvote/{id}", method = RequestMethod.GET)
    public ResponseEntity downvoteAnswer(@PathVariable("id") Integer questionId) {

        return new ResponseEntity(HttpStatus.OK);
    }
}