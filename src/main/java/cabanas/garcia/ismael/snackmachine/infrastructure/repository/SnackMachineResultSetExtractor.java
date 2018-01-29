package cabanas.garcia.ismael.snackmachine.infrastructure.repository;

import cabanas.garcia.ismael.snackmachine.domain.model.SnackMachine;
import cabanas.garcia.ismael.snackmachine.domain.model.SnackMachineId;
import cabanas.garcia.ismael.snackmachine.domain.model.Slot;
import cabanas.garcia.ismael.snackmachine.domain.model.SlotId;
import cabanas.garcia.ismael.snackmachine.domain.model.SnackPile;
import cabanas.garcia.ismael.snackmachine.domain.model.Snack;
import cabanas.garcia.ismael.snackmachine.domain.model.SnackId;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SnackMachineResultSetExtractor implements ResultSetExtractor<SnackMachine> {

    public SnackMachineResultSetExtractor() {
        super();
    }

    @Override
    public SnackMachine extractData(ResultSet rs) throws SQLException, DataAccessException {
        SnackMachine.Builder builder = SnackMachine.builder(new SnackMachineId(rs.getString("SM_ID")))
                .setOneCentCount(rs.getInt("SM_ONE_CENT_COUNT"))
                .setTenCentCount(rs.getInt("SM_TEN_CENT_COUNT"))
                .setQuarterCentCount(rs.getInt("SM_QUARTER_CENT_COUNT"))
                .setOneDollarCount(rs.getInt("SM_ONE_DOLLAR_COUNT"))
                .setFiveDollarCount(rs.getInt("SM_FIVE_DOLLAR_COUNT"))
                .setTwentyDollarCount(rs.getInt("SM_TWENTY_DOLLAR_COUNT"))
                .setSlotOne(getSlotFromResultSet(rs));

        rs.next();
        builder.setSlotTwo(getSlotFromResultSet(rs));
        rs.next();
        builder.setSlotThird(getSlotFromResultSet(rs));

        return builder.build();
    }

    private Slot getSlotFromResultSet(ResultSet rs) throws SQLException {
        return Slot.builder()
                .setId(new SlotId(rs.getString("SL_ID")))
                .setSnackMachine(SnackMachine.builder(new SnackMachineId(rs.getString("SM_ID"))).build())
                .setPosition(rs.getShort("SL_POSITION"))
                .setSnackPile(
                        SnackPile.builder()
                                .setSnack(Snack.builder(new SnackId(rs.getString("SL_SNACK_ID"))).build())
                                .setQuantity(rs.getInt("SL_QUANTITY"))
                                .setPrice(rs.getBigDecimal("SL_PRICE"))
                                .build())

                .build();
    }
}
