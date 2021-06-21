package com.homeworkoverflow.homeworkoverflowbackend.models;

import java.util.Date;
import java.util.HashMap;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;

public class Answer {
    Long answerid;
    Long questionId;
    String answerContent;
    User answerUser;
    Boolean userUpvoteStatus;
    Date date_posted;
    Integer upvotes;
    Integer comments;

    @Autowired
    public Answer(Long answerid, Long questionContent, String answerContent,
            Date date_posted, Integer upvotes, Integer comments, User answerUser, Boolean userUpvoteStatus) {
        this.answerid = answerid;
        this.answerContent = answerContent;
        this.date_posted = date_posted;
        this.upvotes = upvotes;
        this.comments = comments;
        this.answerUser = answerUser;
        this.userUpvoteStatus = userUpvoteStatus;
    }

    public Answer() {}

    public Long getQuestionId() {
        return this.questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    public String getAnswerContent() {
        return this.answerContent;
    }

    public void setAnswerContent(String answerContent) {
        this.answerContent = answerContent;
    }

    public Long getAnswer_id() {
        return this.answerid;
    }

    public void setAnswer_id(Long answerid) {
        this.answerid = answerid;
    }

    public User getAnswerUser() {
        return this.answerUser;
    }

    public void setAnswerUser(User answerUser) {
        this.answerUser = answerUser;
    }

    public Date getDate_posted() {
        return this.date_posted;
    }

    public void setDate_posted(Date date_posted) {
        this.date_posted = date_posted;
    }

    public Integer getUpvotes() {
        return this.upvotes;
    }

    public void setUpvotes(Integer upvotes) {
        this.upvotes = upvotes;
    }

    public Integer getComments() {
        return this.comments;
    }

    public void setComments(Integer comments) {
        this.comments = comments;
    }

    public Boolean getUserUpvoteStatus() {
        return this.userUpvoteStatus;
    }

    public void setUserUpvoteStatus(Boolean userUpvoteStatus) {
        this.userUpvoteStatus = userUpvoteStatus;
    }

    @Override
    public String toString() {
        try {
            return new ObjectMapper().writeValueAsString(this);
        } catch (JsonProcessingException e) {
            e.printStackTrace();

            return "";
        }
    }

    public static Answer mapJsonToAnswer(HashMap<String, Object> obj, User user) {
        Answer answer = new Answer();

        answer.setAnswerContent((String) obj.get("answerContent"));
        answer.setAnswerUser(user);
        answer.setQuestionId((Long) obj.get("questionid"));

        return answer;
    }

}