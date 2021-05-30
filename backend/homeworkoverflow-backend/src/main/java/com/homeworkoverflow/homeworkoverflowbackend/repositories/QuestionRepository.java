package com.homeworkoverflow.homeworkoverflowbackend.repositories;

import java.util.List;

import com.homeworkoverflow.homeworkoverflowbackend.models.Question;
import com.homeworkoverflow.homeworkoverflowbackend.utils.QuestionRowMapper;

import org.springframework.beans.factory.annotation.Autowired;
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
            return null;
        }
    }

    public Question findId(Integer questionid) {
        try {
            
            String sql_query = String.format("SELECT * FROM tblquestion WHERE questionid= %s;", questionid);

            Question question = jdbcTemplate.queryForObject(sql_query, new QuestionRowMapper());
            return question;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
