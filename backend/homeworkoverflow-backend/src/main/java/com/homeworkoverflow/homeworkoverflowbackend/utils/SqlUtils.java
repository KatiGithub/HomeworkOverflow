package com.homeworkoverflow.homeworkoverflowbackend.utils;

import java.sql.Array;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

public class SqlUtils {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Array createSqlArray(List<String> strList) {
        Array strArray = null;
        try {
            strArray = jdbcTemplate.getDataSource().getConnection().createArrayOf("text", strList.toArray());
        } catch (SQLException ignore) {}

        return strArray;
    }
}
