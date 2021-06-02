package com.homeworkoverflow.homeworkoverflowbackend.models;

import java.util.Date;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;

public class Answer {
    Long answer_id;
    Long questionId;
    String answerContent;
    Long answeruserid;

    
    Date date_posted;
    Integer upvotes;
    Integer comments;

    @Autowired
    public Answer(Long answer_id, Long questionContent, String answerContent,
            Date date_posted, Integer upvotes, Integer comments, Long answeruserid) {
        this.answer_id = answer_id;
        this.answerContent = answerContent;
        this.date_posted = date_posted;
        this.upvotes = upvotes;
        this.comments = comments;
        this.answeruserid = answeruserid;
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
        return this.answer_id;
    }

    public void setAnswer_id(Long answer_id) {
        this.answer_id = answer_id;
    }

    public Long getAnsweruserid() {
        return this.answeruserid;
    }

    public void setAnsweruserid(Long answeruserid) {
        this.answeruserid = answeruserid;
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

    @Override
    public String toString() {
        try {
            return new ObjectMapper().writeValueAsString(this);
        } catch (JsonProcessingException e) {
            e.printStackTrace();

            return "";
        }
    }

}