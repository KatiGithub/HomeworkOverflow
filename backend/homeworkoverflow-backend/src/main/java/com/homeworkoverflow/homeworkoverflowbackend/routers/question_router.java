package com.homeworkoverflow.homeworkoverflowbackend.routers;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.homeworkoverflow.homeworkoverflowbackend.controllers.QuestionController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class question_router {

    @Autowired
    private QuestionController qController;

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
    public ResponseEntity<String> retrieveQuestionsAnsweredByUsers(@RequestParam Integer userid) {

        return this.qController.retrieveQuestionsAnsweredByUser(userid);
    }

    @RequestMapping(value="/answer/{id}", method = RequestMethod.GET)
    public ResponseEntity<String> retrieveAnswersByAnswerId(@PathVariable("id") Long answerId) {

        return this.qController.retrieveAnswersByAnswerId(answerId);
    }

    @RequestMapping(value="/answer/", method = RequestMethod.GET)
    public ResponseEntity<String> retriveAnswersByQuestionId(@RequestParam Long questionid) {

        return this.qController.retrieveAnswersByQuestionId(questionid);
    }

    @RequestMapping(value="/addquestion/", method = RequestMethod.POST)
    public ResponseEntity submitQuestion(@RequestBody String questionDesc, @RequestHeader("Authorization") String jwtToken) {

        return this.qController.submitQuestion(questionDesc, jwtToken);
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