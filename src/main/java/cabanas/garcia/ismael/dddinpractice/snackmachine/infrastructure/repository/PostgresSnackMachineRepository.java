package cabanas.garcia.ismael.dddinpractice.snackmachine.infrastructure.repository;

import cabanas.garcia.ismael.dddinpractice.snackmachine.domain.model.SnackMachine;
import cabanas.garcia.ismael.dddinpractice.snackmachine.domain.model.SnackMachineId;
import cabanas.garcia.ismael.dddinpractice.snackmachine.domain.repository.SnackMachineRepository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class PostgresSnackMachineRepository implements SnackMachineRepository {

    private static final short FIRST_POSITION = 1;
    private static final short SECOND_POSITION = 2;
    private static final short THIRD_POSITION = 3;
    private static final String SNACK_MACHINE_ID = "snackMachineId";
    private static final String SET_CLAUSE = " SET ";
    private static final String UPDATE_CLAUSE = "UPDATE ";
    private static final String WHERE_CLAUSE = "WHERE ";
    private static final String QUANTITY_FIELD = "quantity";
    private static final String SLOT_ID_FIELD = "slotId";
    private final NamedParameterJdbcTemplate jdbcTemplate;
    private final SnackMachineRowMapper snackMachineRowMapper;

    public PostgresSnackMachineRepository(NamedParameterJdbcTemplate jdbcTemplate, SnackMachineRowMapper snackMachineRowMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.snackMachineRowMapper = snackMachineRowMapper;
    }

    @Override
    public void save(SnackMachine aggregateRoot) {
        Map<String, Object> filterParameters = new HashMap<>();
        filterParameters.put("oneCentCount", aggregateRoot.moneyInside().getOneCentCount());
        filterParameters.put("tenCentCount", aggregateRoot.moneyInside().getTenCentCount());
        filterParameters.put("quarterCentCount", aggregateRoot.moneyInside().getQuarterCentCount());
        filterParameters.put("oneDollarCount", aggregateRoot.moneyInside().getOneDollarCount());
        filterParameters.put("fiveDollarCount", aggregateRoot.moneyInside().getFiveDollarCount());
        filterParameters.put("twentyDollarCount", aggregateRoot.moneyInside().getTwentyDollarCount());
        filterParameters.put(SNACK_MACHINE_ID, aggregateRoot.id().getValue());

        String sql = new StringBuilder()
                .append(UPDATE_CLAUSE).append("SNACK_MACHINE").append(SET_CLAUSE)
                .append("SM_ONE_CENT_COUNT = :").append("oneCentCount").append(", ")
                .append("SM_TEN_CENT_COUNT = :").append("tenCentCount").append(", ")
                .append("SM_QUARTER_CENT_COUNT = :").append("quarterCentCount").append(", ")
                .append("SM_ONE_DOLLAR_COUNT = :").append("oneDollarCount").append(", ")
                .append("SM_FIVE_DOLLAR_COUNT = :").append("fiveDollarCount").append(", ")
                .append("SM_TWENTY_DOLLAR_COUNT = :").append("twentyDollarCount").append(" ")
                .append(WHERE_CLAUSE).append("SM_ID = :").append(SNACK_MACHINE_ID)
                .toString();

        jdbcTemplate.update(sql, filterParameters);
    }

    public void saveWithSlots(SnackMachine aggregateRoot) {
        save(aggregateRoot);

        Map<String, Object> filterParametersUpdateSlot1 = new HashMap<>();
        filterParametersUpdateSlot1.put(QUANTITY_FIELD, aggregateRoot.getSnackPile(FIRST_POSITION).quantity());
        filterParametersUpdateSlot1.put(SLOT_ID_FIELD, aggregateRoot.getSlotId(FIRST_POSITION).getValue());
        filterParametersUpdateSlot1.put(SNACK_MACHINE_ID, aggregateRoot.id().getValue());

        String sqlUpdateSlot1 = new StringBuilder()
                .append(UPDATE_CLAUSE).append("SLOT").append(SET_CLAUSE)
                .append("SL_QUANTITY = :").append(QUANTITY_FIELD).append(" ")
                .append(WHERE_CLAUSE).append("SL_ID = :").append(SLOT_ID_FIELD).append(" ")
                .append("AND ").append("SL_SNACK_MACHINE_ID = :").append(SNACK_MACHINE_ID)
                .toString();

        jdbcTemplate.update(sqlUpdateSlot1, filterParametersUpdateSlot1);

        Map<String, Object> filterParametersUpdateSlot2 = new HashMap<>();
        filterParametersUpdateSlot2.put(QUANTITY_FIELD, aggregateRoot.getSnackPile(SECOND_POSITION).quantity());
        filterParametersUpdateSlot2.put(SLOT_ID_FIELD, aggregateRoot.getSlotId(SECOND_POSITION).getValue());
        filterParametersUpdateSlot2.put(SNACK_MACHINE_ID, aggregateRoot.id().getValue());

        String sqlUpdateSlot2 = new StringBuilder()
                .append(UPDATE_CLAUSE).append("SLOT").append(SET_CLAUSE)
                .append("SL_QUANTITY = :").append(QUANTITY_FIELD).append(" ")
                .append(WHERE_CLAUSE).append("SL_ID = :").append(SLOT_ID_FIELD).append(" ")
                .append("AND ").append("SL_SNACK_MACHINE_ID = :").append(SNACK_MACHINE_ID)
                .toString();

        jdbcTemplate.update(sqlUpdateSlot2, filterParametersUpdateSlot2);

        Map<String, Object> filterParametersUpdateSlot3 = new HashMap<>();
        filterParametersUpdateSlot3.put(QUANTITY_FIELD, aggregateRoot.getSnackPile(THIRD_POSITION).quantity());
        filterParametersUpdateSlot3.put(SLOT_ID_FIELD, aggregateRoot.getSlotId(THIRD_POSITION).getValue());
        filterParametersUpdateSlot3.put(SNACK_MACHINE_ID, aggregateRoot.id().getValue());

        String sqlUpdateSlot3 = new StringBuilder()
                .append(UPDATE_CLAUSE).append("SLOT").append(SET_CLAUSE)
                .append("SL_QUANTITY = :").append(QUANTITY_FIELD).append(" ")
                .append(WHERE_CLAUSE).append("SL_ID = :").append(SLOT_ID_FIELD).append(" ")
                .append("AND ").append("SL_SNACK_MACHINE_ID = :").append(SNACK_MACHINE_ID)
                .toString();

        jdbcTemplate.update(sqlUpdateSlot3, filterParametersUpdateSlot3);

    }

    @Override
    public Optional<SnackMachine> findById(SnackMachineId snackMachineId) {
        Map<String, Object> filterParameters = new HashMap<>();
        filterParameters.put(SNACK_MACHINE_ID, snackMachineId.getValue());

        String sql = new StringBuilder()
                .append("SELECT ").append("SM_ID, SM_ONE_CENT_COUNT, SM_TEN_CENT_COUNT, SM_QUARTER_CENT_COUNT, ")
                .append("SM_ONE_DOLLAR_COUNT, SM_FIVE_DOLLAR_COUNT, SM_TWENTY_DOLLAR_COUNT, SL_ID, SL_QUANTITY, ")
                .append("SL_POSITION, SL_PRICE, SL_SNACK_ID ")
                .append("FROM ").append("SNACK_MACHINE ")
                .append("INNER JOIN ").append("SLOT ")
                .append("ON SL_SNACK_MACHINE_ID=SM_ID ")
                .append(WHERE_CLAUSE).append("SM_ID = :").append(SNACK_MACHINE_ID).append(" ")
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
