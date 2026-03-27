package com.pd.patchnumberrequestapp.repository;

import com.pd.patchnumberrequestapp.model.PatchConfig;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class PatchConfigRepository {
    private final JdbcTemplate jdbcTemplate;

    public PatchConfigRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<PatchConfig> rowMapper = (rs, rowNum) -> {
        PatchConfig config = new PatchConfig();
        config.setId(rs.getLong("id"));
        config.setDateFormat(rs.getString("date_format"));
        config.setSequenceLength(rs.getInt("sequence_length"));
        config.setOpenBookLastDeployedPatch(rs.getString("open_book_last_deployed_patch"));
        config.setClosedBookLastDeployedPatch(rs.getString("closed_book_last_deployed_patch"));
        return config;
    };

    public PatchConfig getConfig() {
        String sql = "SELECT * FROM TOOL_PATCH_CONFIG";
        return jdbcTemplate.query(sql, rowMapper).stream().findFirst().orElse(null);
    }

    public void updateConfig(PatchConfig config) {
        String sql = "UPDATE TOOL_PATCH_CONFIG SET date_format = ?, sequence_length = ?, open_book_last_deployed_patch = ?, closed_book_last_deployed_patch = ? WHERE id = ?";
        jdbcTemplate.update(sql, config.getDateFormat(), config.getSequenceLength(), 
                            config.getOpenBookLastDeployedPatch(), config.getClosedBookLastDeployedPatch(), 
                            config.getId());
    }
}
