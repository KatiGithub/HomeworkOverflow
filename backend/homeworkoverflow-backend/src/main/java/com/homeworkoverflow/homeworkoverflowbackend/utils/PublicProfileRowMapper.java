package com.homeworkoverflow.homeworkoverflowbackend.utils;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.homeworkoverflow.homeworkoverflowbackend.models.User;

import org.springframework.jdbc.core.RowMapper;

public class PublicProfileRowMapper implements RowMapper<User> {

    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        User user = new User();
        user.setUserid(rs.getLong("userid"));
        user.setFirstname(rs.getString("firstname"));
        user.setLastname(rs.getString("lastname"));
        user.setUsername(rs.getString("username"));
        user.setEmail(rs.getString("email"));
        user.setGithubhandle(rs.getString("githubhandle"));
        user.setTwitterhandle(rs.getString("twitterhandle"));
        user.setInstagramhandle(rs.getString("instagramhandle"));
        user.setUserlocation(rs.getString("userlocation"));
        user.setTitle(rs.getString("title"));

        return user;
    }

}
