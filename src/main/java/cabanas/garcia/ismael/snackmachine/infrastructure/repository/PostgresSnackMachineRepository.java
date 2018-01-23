package cabanas.garcia.ismael.snackmachine.infrastructure.repository;

import cabanas.garcia.ismael.snackmachine.domain.model.SnackMachine;
import cabanas.garcia.ismael.snackmachine.domain.repository.BaseRepository;
import cabanas.garcia.ismael.snackmachine.domain.repository.SnackMachineRepository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.io.Serializable;
import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

public class PostgresSnackMachineRepository extends BaseRepository<SnackMachine> implements SnackMachineRepository {

    private static final short FIRST_POSITION = 1;
    private static final short SECOND_POSITION = 2;
    private static final short THIRD_POSITION = 3;
    private final NamedParameterJdbcTemplate jdbcTemplate;
    private final SnackMachineRowMapper snackMachineRowMapper;

    public PostgresSnackMachineRepository(NamedParameterJdbcTemplate jdbcTemplate, SnackMachineRowMapper snackMachineRowMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.snackMachineRowMapper = snackMachineRowMapper;
    }

    public void save(SnackMachine aggregateRoot) {
        Map<String, Object> filterParameters = new HashMap<>();
        filterParameters.put("oneCentCount", aggregateRoot.moneyInside().getOneCentCount());
        filterParameters.put("tenCentCount", aggregateRoot.moneyInside().getTenCentCount());
        filterParameters.put("quarterCentCount", aggregateRoot.moneyInside().getQuarterCentCount());
        filterParameters.put("oneDollarCount", aggregateRoot.moneyInside().getOneDollarCount());
        filterParameters.put("fiveDollarCount", aggregateRoot.moneyInside().getFiveDollarCount());
        filterParameters.put("twentyDollarCount", aggregateRoot.moneyInside().getTwentyDollarCount());
        filterParameters.put("snackMachineId", aggregateRoot.id().getValue());

        String sql = new StringBuilder()
                .append("UPDATE ").append("SNACK_MACHINE").append(" SET ")
                .append("SM_ONE_CENT_COUNT = :").append("oneCentCount").append(", ")
                .append("SM_TEN_CENT_COUNT = :").append("tenCentCount").append(", ")
                .append("SM_QUARTER_CENT_COUNT = :").append("quarterCentCount").append(", ")
                .append("SM_ONE_DOLLAR_COUNT = :").append("oneDollarCount").append(", ")
                .append("SM_FIVE_DOLLAR_COUNT = :").append("fiveDollarCount").append(", ")
                .append("SM_TWENTY_DOLLAR_COUNT = :").append("twentyDollarCount").append(" ")
                .append("WHERE ").append("SM_ID = :").append("snackMachineId")
                .toString();

        jdbcTemplate.update(sql, filterParameters);

        Map<String, Object> filterParametersUpdateSlot1 = new HashMap<>();
        filterParametersUpdateSlot1.put("quantity", aggregateRoot.getSnackPile(FIRST_POSITION).quantity());
        filterParametersUpdateSlot1.put("slotId", aggregateRoot.getSlotId(FIRST_POSITION).getValue());
        filterParametersUpdateSlot1.put("snackMachineId", aggregateRoot.id().getValue());

        String sqlUpdateSlot1 = new StringBuilder()
                .append("UPDATE ").append("SLOT").append(" SET ")
                .append("SL_QUANTITY = :").append("quantity").append(" ")
                .append("WHERE ").append("SL_ID = :").append("slotId").append(" ")
                .append("AND ").append("SL_SNACK_MACHINE_ID = :").append("snackMachineId")
                .toString();

        jdbcTemplate.update(sqlUpdateSlot1, filterParametersUpdateSlot1);

        Map<String, Object> filterParametersUpdateSlot2 = new HashMap<>();
        filterParametersUpdateSlot2.put("quantity", aggregateRoot.getSnackPile(SECOND_POSITION).quantity());
        filterParametersUpdateSlot2.put("slotId", aggregateRoot.getSlotId(SECOND_POSITION).getValue());
        filterParametersUpdateSlot2.put("snackMachineId", aggregateRoot.id().getValue());

        String sqlUpdateSlot2 = new StringBuilder()
                .append("UPDATE ").append("SLOT").append(" SET ")
                .append("SL_QUANTITY = :").append("quantity").append(" ")
                .append("WHERE ").append("SL_ID = :").append("slotId").append(" ")
                .append("AND ").append("SL_SNACK_MACHINE_ID = :").append("snackMachineId")
                .toString();

        jdbcTemplate.update(sqlUpdateSlot2, filterParametersUpdateSlot2);

        Map<String, Object> filterParametersUpdateSlot3 = new HashMap<>();
        filterParametersUpdateSlot3.put("quantity", aggregateRoot.getSnackPile(THIRD_POSITION).quantity());
        filterParametersUpdateSlot3.put("slotId", aggregateRoot.getSlotId(THIRD_POSITION).getValue());
        filterParametersUpdateSlot3.put("snackMachineId", aggregateRoot.id().getValue());

        String sqlUpdateSlot3 = new StringBuilder()
                .append("UPDATE ").append("SLOT").append(" SET ")
                .append("SL_QUANTITY = :").append("quantity").append(" ")
                .append("WHERE ").append("SL_ID = :").append("slotId").append(" ")
                .append("AND ").append("SL_SNACK_MACHINE_ID = :").append("snackMachineId")
                .toString();

        jdbcTemplate.update(sqlUpdateSlot3, filterParametersUpdateSlot3);

    }

    @Override
    public Optional<SnackMachine> getById(String snackMachineId) {
        Map<String, Object> filterParameters = new HashMap<>();
        filterParameters.put("snackMachineId", snackMachineId);

        String sql = new StringBuilder()
                .append("SELECT ").append("SM_ID, SM_ONE_CENT_COUNT, SM_TEN_CENT_COUNT, SM_QUARTER_CENT_COUNT, ")
                .append("SM_ONE_DOLLAR_COUNT, SM_FIVE_DOLLAR_COUNT, SM_TWENTY_DOLLAR_COUNT, SL_ID, SL_QUANTITY, SL_POSITION, SL_PRICE, SL_SNACK_ID ")
                .append("FROM ").append("SNACK_MACHINE ")
                .append("INNER JOIN ").append("SLOT ")
                .append("ON SL_SNACK_MACHINE_ID=SM_ID ")
                .append("WHERE ").append("SM_ID = :").append("snackMachineId").append(" ")
                .append("ORDER BY SL_ID")
                .toString();

        return executeQueryForObject(sql, filterParameters);
    }

    private Optional<SnackMachine> executeQueryForObject(String sql, Map<String, Object> filterParameters) {
        Optional result;

        try {
            result =  Optional.of(jdbcTemplate.queryForObject(sql, filterParameters, snackMachineRowMapper));
        } catch (EmptyResultDataAccessException e) {
            result = Optional.empty();
        }

        return result;
    }
}
