package com.homeworkoverflow.homeworkoverflowbackend.models;

import java.util.List;
import java.util.ArrayList;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.hibernate.annotations.NamedQuery;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.type.ListType;
import org.springframework.beans.factory.annotation.Autowired;

@NamedQuery(
    name = "get_question_some",
    query = "from Question",
    fetchSize = 20
)

@TypeDef(
    name = "list-array",
    typeClass = ArrayList.class
)

@Entity
@Table(name = "tblquestion")
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer questionId;

    @Column(name="questiontitle")
    private String questionTitle;

    @Column(name = "questioncontent")
    private String questionContent;

    @Column(name = "askeremail")
    private String askeremail;

    @Type(type = "list-array")
    @Column(name = "tags", columnDefinition = "_text")
    private List<String> tags;

    @Column(name = "dateasked")
    private Date dateAsked;

    @Column(name = "numberofcomments")
    private Integer numberOfComments;

    @Column(name="numberofupvotes")
    private Integer upvotes;

    @Autowired(required = true)
    public Question(Integer questionId, String questionTitle, String questionContent, String askeremail,
            List<String> tags, Date dateAsked, Integer numberOfComments, Integer upvotes) {
        this.questionId = questionId;
        this.questionTitle = questionTitle;
        this.questionContent = questionContent;
        this.askeremail = askeremail;
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

    public String getAskeremail() {
        return this.askeremail;
    }

    public void setAskeremail(String askeremail) {
        this.askeremail = askeremail;
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
