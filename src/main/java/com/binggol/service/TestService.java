package com.binggol.service;

import com.binggol.entity.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class TestService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Test> getList(){
        String sql ="select * from test";
        return (List<Test>) jdbcTemplate.query(sql, new RowMapper<Test>() {
            @Override
            public Test mapRow(ResultSet rs,int rowNum) throws SQLException{
                Test test = new Test();
                test.setName("小明");
                test.setAge("25");
                test.setSex('男');
                return test;
            }
        });
    }
}
