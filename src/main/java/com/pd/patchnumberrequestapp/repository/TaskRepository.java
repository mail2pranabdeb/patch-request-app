package com.pd.patchnumberrequestapp.repository;

import com.pd.patchnumberrequestapp.model.Task;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class TaskRepository {
    private final JdbcTemplate jdbcTemplate;

    public TaskRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<Task> rowMapper = (rs, rowNum) -> {
        Task task = new Task();
        task.setId(rs.getLong("id"));
        try { task.setPatchType(rs.getString("patch_type")); } catch (Exception e) {}
        task.setBookType(rs.getString("book_type"));
        task.setLineType(rs.getString("line_type"));
        task.setTaskNumber(rs.getString("task_number"));
        task.setTaskShortDescription(rs.getString("task_short_description"));
        task.setRequestedBy(rs.getString("requested_by"));
        task.setPatchNumber(rs.getString("patch_number"));
        try {
            java.sql.Timestamp ts = rs.getTimestamp("created_at");
            if (ts != null) task.setCreatedAt(ts.toLocalDateTime());
        } catch (Exception e) {}
        return task;
    };

    public List<Task> findAll() {
        return jdbcTemplate.query("SELECT * FROM TOOL_APP_TASK ORDER BY id DESC", rowMapper);
    }

    public Optional<Task> findById(Long id) {
        String sql = "SELECT * FROM TOOL_APP_TASK WHERE id = ?";
        return jdbcTemplate.query(sql, rowMapper, id).stream().findFirst();
    }

    public void save(Task task) {
        if (task.getId() == null) {
            org.springframework.jdbc.support.KeyHolder keyHolder = new org.springframework.jdbc.support.GeneratedKeyHolder();
            jdbcTemplate.update(connection -> {
                java.sql.PreparedStatement ps = connection.prepareStatement(
                    "INSERT INTO TOOL_APP_TASK (id, patch_type, book_type, line_type, task_number, task_short_description, requested_by, patch_number, created_at) VALUES (TOOL_PATCH_REQUEST_SEQ.nextval, ?, ?, ?, ?, ?, ?, ?, CURRENT_TIMESTAMP)",
                    new String[]{"ID"});
                ps.setString(1, task.getPatchType());
                ps.setString(2, task.getBookType());
                ps.setString(3, task.getLineType());
                ps.setString(4, task.getTaskNumber());
                ps.setString(5, task.getTaskShortDescription());
                ps.setString(6, task.getRequestedBy());
                ps.setString(7, task.getPatchNumber());
                return ps;
            }, keyHolder);
            if (keyHolder.getKey() != null) {
                task.setId(keyHolder.getKey().longValue());
            }
        } else {
            String sql = "UPDATE TOOL_APP_TASK SET patch_type = ?, book_type = ?, line_type = ?, task_number = ?, task_short_description = ?, requested_by = ?, patch_number = ? WHERE id = ?";
            jdbcTemplate.update(sql, task.getPatchType(), task.getBookType(), task.getLineType(),
                                task.getTaskNumber(), task.getTaskShortDescription(),
                                task.getRequestedBy(), task.getPatchNumber(), task.getId());
        }
    }

    public void deleteById(Long id) {
        jdbcTemplate.update("DELETE FROM TOOL_APP_TASK WHERE id = ?", id);
    }
}
