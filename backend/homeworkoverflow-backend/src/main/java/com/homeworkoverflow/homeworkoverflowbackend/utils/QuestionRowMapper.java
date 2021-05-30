package com.homeworkoverflow.homeworkoverflowbackend.utils;

import java.sql.Array;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.homeworkoverflow.homeworkoverflowbackend.models.Question;

import org.springframework.jdbc.core.RowMapper;

public class QuestionRowMapper implements RowMapper<Question> {
    @Override
    public Question mapRow(ResultSet rs, int rowNum) throws SQLException {
        Question question = new Question();

        question.setQuestionId(rs.getInt("questionid"));
        question.setAskeremail(rs.getString("askeremail"));
        question.setNumberOfComments(rs.getInt("numberofcomments"));
        question.setQuestionContent(rs.getString("questioncontent"));
        question.setQuestionTitle(rs.getString("questiontitle"));
        question.setDateAsked(rs.getDate("dateasked"));
        question.setUpvotes(rs.getInt("numberofupvotes"));
        // question.setTags((List<String>) rs.getObject("tags", List.class));

        List<String> tags = Arrays.stream((String[]) rs.getArray("tags").getArray()).collect(Collectors.toList());
        question.setTags(tags);


        return question;
    }
}
