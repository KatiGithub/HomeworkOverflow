package com.homeworkoverflow.homeworkoverflowbackend.repositories;

import java.util.List;

import com.homeworkoverflow.homeworkoverflowbackend.models.Answer;
import com.homeworkoverflow.homeworkoverflowbackend.models.Question;
import com.homeworkoverflow.homeworkoverflowbackend.utils.AnswerRowMapper;
import com.homeworkoverflow.homeworkoverflowbackend.utils.QuestionRowMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import org.springframework.stereotype.Component;

@Component
public class QuestionRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Question> findHome() {

        try {
            List<Question> questions = jdbcTemplate.query("SELECT * FROM tblquestion ORDER BY RANDOM() LIMIT 20;", new QuestionRowMapper());
            return questions;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw ex;
        }
    }

    public Question findId(Integer questionid) {
        try {
            String sql_query = String.format("SELECT * FROM tblquestion WHERE questionid= %s;", questionid);

            Question question = jdbcTemplate.queryForObject(sql_query, new QuestionRowMapper());
            return question;
        } catch (Exception ex) {
            throw ex;
        }
    }

    public List<Answer> findAnswersByQuestionId(Integer questionid) {
        try {
            String sql_query = String.format(
                "SELECT * FROM tblanswer WHERE questionid = %s;",
                questionid);
            List<Answer> answers = jdbcTemplate.query(sql_query, new AnswerRowMapper());

            return answers;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public List<Answer> findAnswersByUserId(Long userid) {
        try {
            String sql_query = String.format("SELECT tblanswer.* FROM tblanswer INNER JOIN tbluser ON tblanswer.answeruserid = tbluser.userid WHERE answeruserid = %d;", userid);
            List<Answer> answers = jdbcTemplate.query(sql_query, new AnswerRowMapper());
            return answers;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
    
    public void submitQuestion(Question question) {
        try {
            String sql_query = String.format("INSERT INTO tblquestion(questiontitle, questioncontent, tags, dateasked, numberofcomments, numberofupvotes, askeruserid) VALUES('%s', '%s', '%s', clock_timestamp(),%d, %d, %d)",
            question.getQuestionTitle(),
            question.getQuestionContent(),
            question.getTags().toString().
                replace("[", "{").
                replace("]", "}").
                replace("\"", ""),
            question.getNumberOfComments(),
            question.getUpvotes(),
            question.getAskeruserid()
            );

            jdbcTemplate.execute(sql_query);
        } catch (DataAccessException ex) {
            ex.printStackTrace();
        }
    }

    public Answer findAnswerbyAnswerId(Long answerid) {
        try {
            String sql_query = String.format("SELECT * FROM tblanswer WHERE answerid = %d", answerid);
            Answer answer = jdbcTemplate.queryForObject(sql_query, new AnswerRowMapper());

            return answer;
        } catch (DataAccessException ex) {
            ex.printStackTrace();
            throw ex;
        }
    }

    public List<Answer> findAnswersByQuestionId(Long questionid) {
        try {
            String sql_query = String.format("SELECT * FROM tblanswer WHERE questionid = %d", questionid);
            List<Answer> answers = jdbcTemplate.query(sql_query, new AnswerRowMapper());

            return answers;
        } catch (DataAccessException ex) {
            ex.printStackTrace();
            throw ex;
        }
    }

    // public Integer retrieveUpvotestoAnswer(Long answerid) {
    //     try {
    //         String sql_query = String.format("SELECT COUNT(*) FROM tblupvote WHERE answerid = %d;", answerid);

    //         Integer upvotes = jdbcTemplate.queryForObject(sql_query, Integer.class);

    //         return upvotes;
    //     } catch (DataAccessException ex) {
    //         throw ex;
    //     }
    // }

    public void upvoteAnswer(Long answerid, Long userid) {
        try {
            String insert_tblupvote_query = String.format("BEGIN TRANSACTION; INSERT INTO tblupvote(answerid, userid) VALUES(%d, %d); ",
            answerid,
            userid);

            String update_tblanswer_query = String.format("UPDATE tblanswer SET upvotes = upvotes + 1 WHERE answerid = %d; COMMIT TRANSACTION;", 
            answerid);

            String sql_query = insert_tblupvote_query + update_tblanswer_query;
            System.out.println(sql_query);

            jdbcTemplate.execute(sql_query);
        } catch (DataAccessException ex) {
            throw ex;
        }
    }
}
