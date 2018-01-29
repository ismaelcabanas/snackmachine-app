package cabanas.garcia.ismael.atm.infrastructure.repository;

import cabanas.garcia.ismael.atm.domain.model.Atm;
import cabanas.garcia.ismael.atm.domain.model.AtmId;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AtmRowMapper implements RowMapper<Atm> {
    @Override
    public Atm mapRow(ResultSet rs, int rowNum) throws SQLException {
        return Atm.builder(new AtmId(rs.getString("AM_ID")))
                .setMoneyCharged(rs.getDouble("AM_AMOUNT_CHARGED"))
                .setOneCentCount(rs.getInt("AM_ONE_CENT_COUNT"))
                .setTenCentCount(rs.getInt("AM_TEN_CENT_COUNT"))
                .setQuarterCentCount(rs.getInt("AM_QUARTER_CENT_COUNT"))
                .setOneDollarCount(rs.getInt("AM_ONE_DOLLAR_COUNT"))
                .setFiveDollarCount(rs.getInt("AM_FIVE_DOLLAR_COUNT"))
                .setTwentyDollarCount(rs.getInt("AM_TWENTY_DOLLAR_COUNT"))
                .build();
    }
}
