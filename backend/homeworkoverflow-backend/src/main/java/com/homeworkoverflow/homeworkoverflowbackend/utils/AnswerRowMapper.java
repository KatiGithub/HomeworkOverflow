package com.homeworkoverflow.homeworkoverflowbackend.utils;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.homeworkoverflow.homeworkoverflowbackend.models.Answer;

import org.springframework.jdbc.core.RowMapper;

public class AnswerRowMapper implements RowMapper<Answer> {

    @Override
    public Answer mapRow(ResultSet rs, int rowNum) throws SQLException {
        Answer answer = new Answer();

        answer.setAnswer_id(rs.getLong("answerId"));
        answer.setQuestionId(rs.getLong("questionid"));
        answer.setAnsweruserid(rs.getLong("answeruserid"));
        answer.setAnswerContent(rs.getString("answercontent"));
        answer.setDate_posted(rs.getDate("dateposted"));
        answer.setComments(rs.getInt("numberofcomments"));
        answer.setUpvotes(rs.getInt("numberofupvotes"));

        return answer;
    }
    
}
