package cabanas.garcia.ismael.dddinpractice.management.infrastructure.repository;

import cabanas.garcia.ismael.dddinpractice.management.domain.model.HeadOffice;
import cabanas.garcia.ismael.dddinpractice.management.domain.model.HeadOfficeId;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class HeadOfficeRowMapper implements RowMapper<HeadOffice> {
    @Override
    public HeadOffice mapRow(ResultSet rs, int rowNum) throws SQLException {
        return HeadOffice.builder(new HeadOfficeId(rs.getString("HO_ID")))
                .setBalance(rs.getDouble("HO_BALANCE"))
                .build();
    }
}
