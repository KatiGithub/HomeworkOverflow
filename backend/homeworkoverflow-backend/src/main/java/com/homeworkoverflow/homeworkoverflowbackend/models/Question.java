package com.homeworkoverflow.homeworkoverflowbackend.models;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;

public class Question {
    private Integer questionId;
    private String questionTitle;
    private String questionContent;
    private String askerUsername;
    private String[] tags;
    private Date dateAsked;
    private Integer numberOfComments;
    private Integer upvotes;

    @Autowired(required = true)
    public Question(Integer questionId, String questionTitle, String questionContent, String askerUsername,
            String[] tags, Date dateAsked, Integer numberOfComments, Integer upvotes) {
        this.questionId = questionId;
        this.questionTitle = questionTitle;
        this.questionContent = questionContent;
        this.askerUsername = askerUsername;
        this.tags = tags;
        this.dateAsked = dateAsked;
        this.numberOfComments = numberOfComments;
        this.upvotes = upvotes;
    }



    public Integer getQuestionId() {
        return this.questionId;
    }

    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }

    public String getQuestionTitle() {
        return this.questionTitle;
    }

    public void setQuestionTitle(String questionTitle) {
        this.questionTitle = questionTitle;
    }

    public String getQuestionContent() {
        return this.questionContent;
    }

    public void setQuestionContent(String questionContent) {
        this.questionContent = questionContent;
    }

    public String getAskerUsername() {
        return this.askerUsername;
    }

    public void setAskerUsername(String askerUsername) {
        this.askerUsername = askerUsername;
    }

    public String[] getTags() {
        return this.tags;
    }

    public void setTags(String[] tags) {
        this.tags = tags;
    }

    public Date getDateAsked() {
        return this.dateAsked;
    }

    public void setDateAsked(Date dateAsked) {
        this.dateAsked = dateAsked;
    }

    public Integer getNumberOfComments() {
        return this.numberOfComments;
    }

    public void setNumberOfComments(Integer numberOfComments) {
        this.numberOfComments = numberOfComments;
    }

    public Integer getUpvotes() {
        return this.upvotes;
    }

    public void setUpvotes(Integer upvotes) {
        this.upvotes = upvotes;
    }

}
