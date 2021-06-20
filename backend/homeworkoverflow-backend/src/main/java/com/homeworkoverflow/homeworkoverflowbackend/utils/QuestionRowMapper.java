package com.homeworkoverflow.homeworkoverflowbackend.utils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.homeworkoverflow.homeworkoverflowbackend.models.Question;
import com.homeworkoverflow.homeworkoverflowbackend.models.User;

import org.springframework.jdbc.core.RowMapper;

public class QuestionRowMapper implements RowMapper<Question> {
    @Override
    public Question mapRow(ResultSet rs, int rowNum) throws SQLException {
        Question question = new Question();

        question.setQuestionId(rs.getLong("questionid"));
        question.setQuestionContent(rs.getString("questioncontent"));
        question.setQuestionTitle(rs.getString("questiontitle"));
        question.setDateAsked(rs.getDate("dateasked"));

        List<String> tags = Arrays.stream((String[]) rs.getArray("tags").getArray()).collect(Collectors.toList());
        question.setTags(tags);

        // User user = new User();
        // user.setEmail(rs.getString("email"));
        // user.setUsername(rs.getString("username"));
        // user.setUserid(rs.getLong("userid"));
        // user.setFirstname(rs.getString("firstname"));
        // user.setLastname(rs.getString("lastname"));

        // question.setAsker(user);
        
        User user = new PublicProfileRowMapper().mapRow(rs, rowNum);

        question.setAsker(user);

        return question;
    }
}
