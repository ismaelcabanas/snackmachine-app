package cabanas.garcia.ismael.dddinpractice.atm.infrastructure.repository;

import cabanas.garcia.ismael.dddinpractice.atm.domain.model.Atm;
import cabanas.garcia.ismael.dddinpractice.atm.domain.model.AtmId;
import cabanas.garcia.ismael.dddinpractice.atm.domain.repository.AtmRepository;
import cabanas.garcia.ismael.dddinpractice.snackmachine.domain.repository.BaseRepository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class PostgresAtmRepository extends BaseRepository<Atm> implements AtmRepository {

    private static final String ATM_ID = "atmId";
    private final NamedParameterJdbcTemplate jdbcTemplate;
    private final AtmRowMapper atmRowMapper;

    public PostgresAtmRepository(NamedParameterJdbcTemplate jdbcTemplate, AtmRowMapper atmRowMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.atmRowMapper = atmRowMapper;
    }

    @Override
    public void save(Atm aggregateRoot) {
        Map<String, Object> filterParameters = new HashMap<>();
        filterParameters.put("amountCharged", aggregateRoot.moneyCharged());
        filterParameters.put("oneCentCount", aggregateRoot.moneyInside().getOneCentCount());
        filterParameters.put("tenCentCount", aggregateRoot.moneyInside().getTenCentCount());
        filterParameters.put("quarterCentCount", aggregateRoot.moneyInside().getQuarterCentCount());
        filterParameters.put("oneDollarCount", aggregateRoot.moneyInside().getOneDollarCount());
        filterParameters.put("fiveDollarCount", aggregateRoot.moneyInside().getFiveDollarCount());
        filterParameters.put("twentyDollarCount", aggregateRoot.moneyInside().getTwentyDollarCount());
        filterParameters.put(ATM_ID, aggregateRoot.id().getValue());

        String sql = new StringBuilder()
                .append("UPDATE ").append("ATM_MACHINE").append(" SET ")
                .append("AM_AMOUNT_CHARGED = :").append("amountCharged").append(", ")
                .append("AM_ONE_CENT_COUNT = :").append("oneCentCount").append(", ")
                .append("AM_TEN_CENT_COUNT = :").append("tenCentCount").append(", ")
                .append("AM_QUARTER_CENT_COUNT = :").append("quarterCentCount").append(", ")
                .append("AM_ONE_DOLLAR_COUNT = :").append("oneDollarCount").append(", ")
                .append("AM_FIVE_DOLLAR_COUNT = :").append("fiveDollarCount").append(", ")
                .append("AM_TWENTY_DOLLAR_COUNT = :").append("twentyDollarCount").append(" ")
                .append("WHERE ").append("AM_ID = :").append(ATM_ID)
                .toString();

        jdbcTemplate.update(sql, filterParameters);

    }

    @Override
    public Optional<Atm> findById(AtmId atmId) {
        Map<String, Object> filterParameters = new HashMap<>();
        filterParameters.put(ATM_ID, atmId.getValue());

        String sql = new StringBuilder()
                .append("SELECT ").append("AM_ID, AM_AMOUNT_CHARGED, AM_ONE_CENT_COUNT, AM_TEN_CENT_COUNT, AM_QUARTER_CENT_COUNT, ")
                .append("AM_ONE_DOLLAR_COUNT, AM_FIVE_DOLLAR_COUNT, AM_TWENTY_DOLLAR_COUNT ")
                .append("FROM ").append("ATM_MACHINE ")
                .append("WHERE ").append("AM_ID = :").append(ATM_ID).append(" ")
                .append("ORDER BY AM_ID")
                .toString();

        return executeQueryForObject(sql, filterParameters);
    }

    private Optional<Atm> executeQueryForObject(String sql, Map<String, Object> filterParameters) {
        Optional result;

        try {
            result =  Optional.of(jdbcTemplate.queryForObject(sql, filterParameters, atmRowMapper));
        } catch (EmptyResultDataAccessException e) {
            result = Optional.empty();
        }

        return result;
    }
}
