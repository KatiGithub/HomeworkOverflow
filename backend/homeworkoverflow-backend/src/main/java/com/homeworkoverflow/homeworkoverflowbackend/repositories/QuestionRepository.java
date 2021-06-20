package com.homeworkoverflow.homeworkoverflowbackend.repositories;

import java.util.List;

import com.google.protobuf.Empty;
import com.homeworkoverflow.homeworkoverflowbackend.models.Answer;
import com.homeworkoverflow.homeworkoverflowbackend.models.Question;
import com.homeworkoverflow.homeworkoverflowbackend.utils.AnswerRowMapper;
import com.homeworkoverflow.homeworkoverflowbackend.utils.QuestionRowMapper;

import org.apache.commons.lang3.ObjectUtils.Null;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import org.springframework.stereotype.Component;

@Component
public class QuestionRepository {

    private static final String BEGIN_TRANSACTION_STRING = "BEGIN TRANSACTION;";
    private static final String COMMIT_TRANSACTION_STRING = "COMMIT TRANSACTION;";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Question> findHome() {

        try {
            List<Question> questions = jdbcTemplate.query(
                    "SELECT * FROM tblquestion INNER JOIN tbluser ON tblquestion.askeruserid = tbluser.userid ORDER BY RANDOM() LIMIT 20;",
                    new QuestionRowMapper());
            return questions;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw ex;
        }
    }

    public Question findId(Integer questionid) {
        try {
            String sql_query = String.format(
                    "SELECT * FROM tblquestion INNER JOIN tbluser ON tblquestion.askeruserid = tbluser.userid WHERE questionid= %s;",
                    questionid);

            Question question = jdbcTemplate.queryForObject(sql_query, new QuestionRowMapper());
            return question;
        } catch (Exception ex) {
            throw ex;
        }
    }

    public List<Answer> findAnswersByQuestionId(Long questionid) {
        try {
            String sql_query = String.format(
                    "SELECT * FROM tblanswer INNER JOIN tbluser ON tblanswer.answeruserid = tbluser.userid WHERE questionid = %s;",
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
            String sql_query = String.format(
                    "SELECT * FROM tblanswer INNER JOIN tbluser ON tblanswer.answeruserid = tbluser.userid WHERE tblanswer.answeruserid = %d;",
                    userid);
            List<Answer> answers = jdbcTemplate.query(sql_query, new AnswerRowMapper());
            return answers;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public void submitQuestion(Question question) {
        try {
            String sql_query = String.format(
                    "INSERT INTO tblquestion(questiontitle, questioncontent, tags, dateasked, numberofcomments, numberofupvotes, askeruserid) VALUES('%s', '%s', '%s', clock_timestamp(),%d, %d, %d)",
                    question.getQuestionTitle(), question.getQuestionContent(),
                    question.getTags().toString().replace("[", "{").replace("]", "}").replace("\"", ""),
                    question.getNumberOfComments(), question.getUpvotes(), question.getAsker().getUserid());

            jdbcTemplate.execute(sql_query);
        } catch (DataAccessException ex) {
            ex.printStackTrace();
        }
    }

    public Answer findAnswerbyAnswerId(Long answerid) {
        try {
            String sql_query = String.format("SELECT * FROM tblanswer INNER JOIN tbluser ON tbluser.userid = tblanswer.answeruserid WHERE answerid = %d", answerid);
            Answer answer = jdbcTemplate.queryForObject(sql_query, new AnswerRowMapper());

            return answer;
        } catch (DataAccessException ex) {
            ex.printStackTrace();
            throw ex;
        }
    }
    
    
    public Integer upvoteAnswer(Long answerid, Long userid) {
        try {
            
            Boolean currentuser_vote_status = retrieveUserUpvote(answerid, userid);
            
            if (currentuser_vote_status == null) {
                String insert_upvote_tblupvote_query = String.format(
                    "INSERT INTO tblupvote(answerid, userid, upvote_downvote) VALUES(%s, %s, true);",
                    answerid,
                    userid);
                    
                String update_upvotes_tblanswer_query = String
                .format("UPDATE tblanswer SET upvotes = upvotes + 1 WHERE answerid = %s; ", answerid);
                
                StringBuilder final_query = new StringBuilder()
                .append(BEGIN_TRANSACTION_STRING)
                .append(insert_upvote_tblupvote_query)
                .append(update_upvotes_tblanswer_query)
                .append(COMMIT_TRANSACTION_STRING);
                
                jdbcTemplate.execute(final_query.toString());
            } else if (currentuser_vote_status == false) {
                String update_upvote_tblupvote_query = String.format(
                    "UPDATE tblupvote SET upvote_downvote = true WHERE answerid = %s AND userid = %s;", answerid,
                    userid);
                    
                String update_upvotes_tblanswer_query = String
                .format("UPDATE tblanswer SET upvotes = upvotes + 2 WHERE answerid = %s; ", answerid);
                
                StringBuilder final_query = new StringBuilder()
                .append(BEGIN_TRANSACTION_STRING)
                .append(update_upvote_tblupvote_query)
                .append(update_upvotes_tblanswer_query)
                .append(COMMIT_TRANSACTION_STRING);
                
                jdbcTemplate.execute(final_query.toString());
            } else {
                String delete_upvote_tblupvote_query = String
                .format("DELETE FROM tblupvote WHERE userid = %s AND answerid = %s;", userid, answerid);
                String update_upvotes_tblanswer_query = String
                .format("UPDATE tblanswer SET upvotes = upvotes - 1 WHERE answerid = %s;", answerid);
                
                StringBuilder final_query = new StringBuilder()
                .append(BEGIN_TRANSACTION_STRING)
                .append(delete_upvote_tblupvote_query)
                .append(update_upvotes_tblanswer_query)
                .append(COMMIT_TRANSACTION_STRING);
                
                jdbcTemplate.execute(final_query.toString());
            }
                
            Integer upvotes = retrieveUpvotestoAnswer(answerid);
            return upvotes;
        } catch (EmptyResultDataAccessException ex) {
            return null;
        } catch (DataAccessException ex) {
            ex.printStackTrace();
            throw ex;
        } catch(NullPointerException ex) {
            return null;
        }
    }
            
    public Integer downvoteAnswer(Long answerid, Long userid) {
        try {
            
            Boolean currentuser_vote_status = retrieveUserUpvote(answerid, userid);
            
            if (currentuser_vote_status == null) {
                String insert_upvote_tblupvote_query = String.format(
                    "INSERT INTO tblupvote(answerid, userid, upvote_downvote) VALUES(%s, %s, false);",
                    answerid,
                    userid);
                    
                    String update_upvotes_tblanswer_query = String
                    .format("UPDATE tblanswer SET upvotes = upvotes - 1 WHERE answerid = %s; ", answerid);
                    
                    StringBuilder final_query = new StringBuilder()
                    .append(BEGIN_TRANSACTION_STRING)
                    .append(insert_upvote_tblupvote_query)
                    .append(update_upvotes_tblanswer_query)
                    .append(COMMIT_TRANSACTION_STRING);
                    
                    jdbcTemplate.execute(final_query.toString());
            } else if (currentuser_vote_status == true) {
                String update_upvote_tblupvote_query = String.format(
                    "UPDATE tblupvote SET upvote_downvote = false WHERE answerid = %s AND userid = %s;", answerid,
                    userid);
                    
                    String update_upvotes_tblanswer_query = String
                    .format("UPDATE tblanswer SET upvotes = upvotes - 2 WHERE answerid = %s; ", answerid);
                    
                    StringBuilder final_query = new StringBuilder()
                    .append(BEGIN_TRANSACTION_STRING)
                    .append(update_upvote_tblupvote_query)
                    .append(update_upvotes_tblanswer_query)
                    .append(COMMIT_TRANSACTION_STRING);
                    
                    jdbcTemplate.execute(final_query.toString());
            } else {
                String delete_upvote_tblupvote_query = String
                .format("DELETE FROM tblupvote WHERE userid = %s AND answerid = %s;", userid, answerid);
                String update_upvotes_tblanswer_query = String
                .format("UPDATE tblanswer SET upvotes = upvotes + 1 WHERE answerid = %s;", answerid);
                
                StringBuilder final_query = new StringBuilder()
                .append(BEGIN_TRANSACTION_STRING)
                .append(delete_upvote_tblupvote_query)
                .append(update_upvotes_tblanswer_query)
                .append(COMMIT_TRANSACTION_STRING);
                
                jdbcTemplate.execute(final_query.toString());
            }
                    
                Integer upvotes = retrieveUpvotestoAnswer(answerid);
                return upvotes;
            } catch (EmptyResultDataAccessException ex) {
                return null;
            } catch(DataAccessException ex) {
                ex.printStackTrace();
                throw ex;
            } catch(NullPointerException ex) {
                return null;
            }
        }
        private Integer retrieveUpvotestoAnswer(Long answerid) {
            try {
                String sql_query = String.format("SELECT upvotes FROM tblanswer WHERE answerid = %s;", answerid);

                Integer upvotes = jdbcTemplate.queryForObject(sql_query, Integer.class);

                return upvotes;
            } catch (DataAccessException ex) {
                throw ex;
            }
    }

    public Boolean retrieveUserUpvote(Long answerid, Long userid) {
        try {
            String sql_query = String.format("SELECT upvote_downvote FROM tblupvote WHERE answerid = %s AND userid = %s",
            answerid,
            userid
            );

            Boolean currentuser_vote_status = jdbcTemplate.queryForObject(sql_query, Boolean.class);

            return currentuser_vote_status;
        } catch(EmptyResultDataAccessException ex) {
            return null;
        }
    }
}                    