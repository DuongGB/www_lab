/*
 * @ {#} CandidateRepository.java   1.0     10/19/2024
 *
 * Copyright (c) 2024 IUH. All rights reserved.
 */

package vn.edu.iuh.fit.repositories;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/*
 * @description:
 * @author: Nguyen Tan Thai Duong
 * @date:   10/19/2024
 * @version:    1.0
 */
@Repository
public class CandidateRepository {
    private final DataSource dataSource;
    private final JdbcTemplate jdbcTemplate;

    public CandidateRepository(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void insertCandidate() throws SQLException {
        String sql = "INSERT INTO candidate (lastName, middleName, firstName, dob, email, address, phone) VALUES (?, ?, ?, ?, ?, ?, ?)";
        Connection connection = dataSource.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, "Nguyen");
        preparedStatement.setString(2, "Tan Thai");
        preparedStatement.setString(3, "Duong");
        preparedStatement.setDate(4, java.sql.Date.valueOf("2000-10-19"));
        preparedStatement.setString(5, "Duong@gmail.com");
        preparedStatement.setString(6, "HCM");
        preparedStatement.setString(7, "0123456789");
        int rows = preparedStatement.executeUpdate();
        if (rows > 0) {
            System.out.println("A new candidate was inserted successfully!");
        } else {
            System.out.println("A new candidate was not inserted!");
        }
    }
}

