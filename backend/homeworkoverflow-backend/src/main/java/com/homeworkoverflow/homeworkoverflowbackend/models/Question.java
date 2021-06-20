package com.homeworkoverflow.homeworkoverflowbackend.models;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

public class Question {
    private Long questionId;
    private String questionTitle;
    private String questionContent;
    private User asker;
    private List<String> tags;
    private Date dateAsked;
    private Integer numberOfComments = 0;
    private Integer upvotes = 0;

    public Question(Long questionId, String questionTitle, String questionContent, User asker,
            List<String> tags, Date dateAsked, Integer numberOfComments, Integer upvotes) {
        this.questionId = questionId;
        this.questionTitle = questionTitle;
        this.questionContent = questionContent;
        this.asker = asker;
        this.tags = tags;
        this.dateAsked = dateAsked;
        this.numberOfComments = numberOfComments;
        this.upvotes = upvotes;
    }

    public Question(String questionTitle, String questionContent, User asker,
            List<String> tags, Date dateAsked, Integer numberOfComments, Integer upvotes) {
        this.questionTitle = questionTitle;
        this.questionContent = questionContent;
        this.asker = asker;
        this.tags = tags;
        this.dateAsked = dateAsked;
        this.numberOfComments = numberOfComments;
        this.upvotes = upvotes;
    }

    public Question() {}

    public User getAsker() {
        return this.asker;
    }

    public void setAsker(User asker) {
        this.asker = asker;
    }

    public Long getQuestionId() {
        return this.questionId;
    }

    public void setQuestionId(Long questionId) {
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

    public List<String> getTags() {
        return this.tags;
    }

    public void setTags(List<String> tags) {
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

    @Override

    public String toString() {
        try {
            return new ObjectMapper().writeValueAsString(this);
        } catch (Exception ex) {
            ex.printStackTrace();
            return "";
        }
    }

}
