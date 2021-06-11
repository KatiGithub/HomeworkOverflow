package com.homeworkoverflow.homeworkoverflowbackend.repositories;

import com.homeworkoverflow.homeworkoverflowbackend.models.User;
import com.homeworkoverflow.homeworkoverflowbackend.utils.PublicProfileRowMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class ProfileRepostiory {
    
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public User public_findUserById(Long userid) {
        try {
            String sql_query = String.format("SELECT * FROM tbluser WHERE userid = %d", userid);

            User user = jdbcTemplate.queryForObject(sql_query, new PublicProfileRowMapper());
            return user;
        } catch (DataAccessException ex) {
            ex.printStackTrace();
            throw ex;
        }
    }
}
