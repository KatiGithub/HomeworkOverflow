package com.homeworkoverflow.homeworkoverflowbackend.repositories;

import com.homeworkoverflow.homeworkoverflowbackend.models.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class AuthRepository {
    
    @Autowired
    JdbcTemplate jdbcTemplate;

    public void signUpUser(User user) {
        String sql_query = String.format("INSERT INTO tbluser (firstname, lastname, email, firebaseuid) VALUES('%s', '%s', '%s', '%s');",
        user.getFirstname(),
        user.getLastname(),
        user.getEmail(),
        user.getFirebaseuid());
    
        try {
            jdbcTemplate.execute(sql_query);
        } catch(DataAccessException ex) {
            ex.printStackTrace();
            throw ex;
        }
    }

    public Long getUserId(String email) {
        String sql_query = String.format("SELECT userid FROM tbluser WHERE email = '%s';", email);
        try {
            Long userid = jdbcTemplate.queryForObject(sql_query, Long.class);
            return userid;
        } catch (DataAccessException ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
