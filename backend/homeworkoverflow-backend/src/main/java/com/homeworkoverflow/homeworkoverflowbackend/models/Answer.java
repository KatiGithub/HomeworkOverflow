package com.homeworkoverflow.homeworkoverflowbackend.models;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;

public class Answer {
    // Boolean upvote;
    // Boolean downvote;
    Integer answer_id;
    Integer questionId;
    String answerContent;
    String answer_username;
    Date date_posted;
    Integer upvotes;
    Integer comments;

    @Autowired
    public Answer(Integer answer_id, Integer questionContent, String answerContent, String answer_username,
            Date date_posted, Integer upvotes, Integer comments) {
        // this.upvote = upvote;
        // this.downvote = downvote;
        this.answer_id = answer_id;
        this.questionId = questionId;
        this.answerContent = answerContent;
        this.answer_username = answer_username;
        this.date_posted = date_posted;
        this.upvotes = upvotes;
        this.comments = comments;
    }

    public Integer getQuestionId() {
        return this.questionId;
    }

    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }

    public String getAnswerContent() {
        return this.answerContent;
    }

    public void setAnswerContent(String answerContent) {
        this.answerContent = answerContent;
    }

    public Integer getAnswer_id() {
        return this.answer_id;
    }

    public void setAnswer_id(Integer answer_id) {
        this.answer_id = answer_id;
    }

    public String getAnswer_username() {
        return this.answer_username;
    }

    public void setAnswer_username(String answer_username) {
        this.answer_username = answer_username;
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

}