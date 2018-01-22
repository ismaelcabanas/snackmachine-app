package cabanas.garcia.ismael.snackmachine.infrastructure.repository;

import cabanas.garcia.ismael.snackmachine.domain.model.SnackMachine;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public final class SnackMachineRowMapper implements RowMapper<SnackMachine> {

    private final SnackMachineResultSetExtractor snackMachineResultSetExtractor;

    public SnackMachineRowMapper(SnackMachineResultSetExtractor snackMachineResultSetExtractor) {
        this.snackMachineResultSetExtractor = snackMachineResultSetExtractor;
    }

    @Override
    public SnackMachine mapRow(ResultSet rs, int rowNum) throws SQLException {
        return snackMachineResultSetExtractor.extractData(rs);
    }
}
