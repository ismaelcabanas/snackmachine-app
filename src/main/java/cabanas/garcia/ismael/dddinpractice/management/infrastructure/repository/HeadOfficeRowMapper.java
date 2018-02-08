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
                .setOneCentCount(rs.getInt("HO_ONE_CENT_COUNT"))
                .setTenCentCount(rs.getInt("HO_TEN_CENT_COUNT"))
                .setQuarterCentCount(rs.getInt("HO_QUARTER_CENT_COUNT"))
                .setOneDollarCount(rs.getInt("HO_ONE_DOLLAR_COUNT"))
                .setFiveDollarCount(rs.getInt("HO_FIVE_DOLLAR_COUNT"))
                .setTwentyDollarCount(rs.getInt("HO_TWENTY_DOLLAR_COUNT"))
                .build();
    }
}
