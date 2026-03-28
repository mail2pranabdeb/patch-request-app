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
        config.setOpenBookRPP1(rs.getLong("open_book_rpp1"));
        config.setOpenBookRPP2(rs.getLong("open_book_rpp2"));
        config.setOpenBookRPP3(rs.getLong("open_book_rpp3"));
        config.setClosedBookRPP1(rs.getLong("closed_book_rpp1"));
        config.setClosedBookRPP2(rs.getLong("closed_book_rpp2"));
        config.setClosedBookRPP3(rs.getLong("closed_book_rpp3"));
        return config;
    };

    public PatchConfig getConfig() {
        String sql = "SELECT * FROM TOOL_PATCH_CONFIG";
        return jdbcTemplate.query(sql, rowMapper).stream().findFirst().orElse(null);
    }

    public void updateConfig(PatchConfig config) {
        String sql = "UPDATE TOOL_PATCH_CONFIG SET open_book_rpp1 = ?, open_book_rpp2 = ?, open_book_rpp3 = ?, closed_book_rpp1 = ?, closed_book_rpp2 = ?, closed_book_rpp3 = ? WHERE id = ?";
        jdbcTemplate.update(sql, 
                            config.getOpenBookRPP1(), config.getOpenBookRPP2(), config.getOpenBookRPP3(),
                            config.getClosedBookRPP1(), config.getClosedBookRPP2(), config.getClosedBookRPP3(),
                            config.getId());
    }
}
