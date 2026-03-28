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
        
        config.setOpenBookPrefix(rs.getString("open_book_prefix"));
        config.setOpenBookFixString(rs.getString("open_book_fix_string"));
        config.setOpenBookMajorVersion(rs.getString("open_book_major_version"));
        config.setOpenBookMinorVersion(rs.getString("open_book_minor_version"));
        config.setOpenBookRPP1(rs.getLong("open_book_rpp1"));
        config.setOpenBookRPP2(rs.getLong("open_book_rpp2"));
        config.setOpenBookRPP3(rs.getLong("open_book_rpp3"));
        config.setOpenBookLastDeployedPatch(rs.getString("open_book_last_deployed_patch"));
        
        config.setClosedBookPrefix(rs.getString("closed_book_prefix"));
        config.setClosedBookFixString(rs.getString("closed_book_fix_string"));
        config.setClosedBookMajorVersion(rs.getString("closed_book_major_version"));
        config.setClosedBookMinorVersion(rs.getString("closed_book_minor_version"));
        config.setClosedBookRPP1(rs.getLong("closed_book_rpp1"));
        config.setClosedBookRPP2(rs.getLong("closed_book_rpp2"));
        config.setClosedBookRPP3(rs.getLong("closed_book_rpp3"));
        config.setClosedBookLastDeployedPatch(rs.getString("closed_book_last_deployed_patch"));
        
        config.setMaxMigPrefix(rs.getString("max_mig_prefix"));
        config.setMaxMigFixString(rs.getString("max_mig_fix_string"));
        config.setMaxMigMajorVersion(rs.getString("max_mig_major_version"));
        config.setMaxMigMinorVersion(rs.getString("max_mig_minor_version"));
        config.setMaxMigRPP1(rs.getLong("max_mig_rpp1"));
        config.setMaxMigRPP2(rs.getLong("max_mig_rpp2"));
        config.setMaxMigRPP3(rs.getLong("max_mig_rpp3"));
        config.setMaxMigLastDeployedPatch(rs.getString("max_mig_last_deployed_patch"));
        
        return config;
    };

    public PatchConfig getConfig() {
        String sql = "SELECT * FROM TOOL_PATCH_CONFIG";
        return jdbcTemplate.query(sql, rowMapper).stream().findFirst().orElse(null);
    }

    public void updateConfig(PatchConfig config) {
        String sql = "UPDATE TOOL_PATCH_CONFIG SET " +
                     "open_book_prefix = ?, open_book_fix_string = ?, open_book_major_version = ?, open_book_minor_version = ?, open_book_rpp1 = ?, open_book_rpp2 = ?, open_book_rpp3 = ?, open_book_last_deployed_patch = ?, " +
                     "closed_book_prefix = ?, closed_book_fix_string = ?, closed_book_major_version = ?, closed_book_minor_version = ?, closed_book_rpp1 = ?, closed_book_rpp2 = ?, closed_book_rpp3 = ?, closed_book_last_deployed_patch = ?, " +
                     "max_mig_prefix = ?, max_mig_fix_string = ?, max_mig_major_version = ?, max_mig_minor_version = ?, max_mig_rpp1 = ?, max_mig_rpp2 = ?, max_mig_rpp3 = ?, max_mig_last_deployed_patch = ? WHERE id = ?";
        jdbcTemplate.update(sql, 
                            config.getOpenBookPrefix(), config.getOpenBookFixString(), config.getOpenBookMajorVersion(), config.getOpenBookMinorVersion(), config.getOpenBookRPP1(), config.getOpenBookRPP2(), config.getOpenBookRPP3(), config.getOpenBookLastDeployedPatch(),
                            config.getClosedBookPrefix(), config.getClosedBookFixString(), config.getClosedBookMajorVersion(), config.getClosedBookMinorVersion(), config.getClosedBookRPP1(), config.getClosedBookRPP2(), config.getClosedBookRPP3(), config.getClosedBookLastDeployedPatch(),
                            config.getMaxMigPrefix(), config.getMaxMigFixString(), config.getMaxMigMajorVersion(), config.getMaxMigMinorVersion(), config.getMaxMigRPP1(), config.getMaxMigRPP2(), config.getMaxMigRPP3(), config.getMaxMigLastDeployedPatch(),
                            config.getId());
    }
}
