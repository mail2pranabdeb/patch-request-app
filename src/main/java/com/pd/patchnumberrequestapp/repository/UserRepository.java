package com.pd.patchnumberrequestapp.repository;

import com.pd.patchnumberrequestapp.model.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class UserRepository {
    private final JdbcTemplate jdbcTemplate;

    public UserRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<User> rowMapper = (rs, rowNum) -> {
        User user = new User();
        user.setId(rs.getLong("ID"));
        user.setUsername(rs.getString("USERNAME"));
        user.setPassword(rs.getString("PASSWORD"));
        user.setRoles(rs.getString("ROLES"));
        return user;
    };

    public Optional<User> findByUsername(String username) {
        String sql = "SELECT * FROM TOOL_APP_USER WHERE UPPER(username) = UPPER(?)";
        return jdbcTemplate.query(sql, rowMapper, username).stream().findFirst();
    }
}
