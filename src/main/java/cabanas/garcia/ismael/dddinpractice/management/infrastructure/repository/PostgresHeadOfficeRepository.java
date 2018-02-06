package cabanas.garcia.ismael.dddinpractice.management.infrastructure.repository;

import cabanas.garcia.ismael.dddinpractice.management.domain.model.HeadOffice;
import cabanas.garcia.ismael.dddinpractice.management.domain.model.HeadOfficeId;
import cabanas.garcia.ismael.dddinpractice.management.domain.repository.HeadOfficeRepository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class PostgresHeadOfficeRepository implements HeadOfficeRepository {

    private static final String HEAD_OFFICE_ID = "headOfficeId";
    private final NamedParameterJdbcTemplate jdbcTemplate;
    private final HeadOfficeRowMapper headOfficeRowMapper;

    public PostgresHeadOfficeRepository(NamedParameterJdbcTemplate jdbcTemplate, HeadOfficeRowMapper headOfficeRowMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.headOfficeRowMapper = headOfficeRowMapper;
    }

    @Override
    public void save(HeadOffice aggregateRoot) {
        Map<String, Object> filterParameters = new HashMap<>();
        filterParameters.put("balance", aggregateRoot.balance());
        filterParameters.put(HEAD_OFFICE_ID, aggregateRoot.id().getValue());

        String sql = new StringBuilder()
                .append("UPDATE ").append("HEAD_OFFICE").append(" SET ")
                .append("HO_BALANCE = :").append("balance").append(" ")
                .append("WHERE ").append("HO_ID = :").append(HEAD_OFFICE_ID)
                .toString();

        jdbcTemplate.update(sql, filterParameters);

    }

    @Override
    public Optional<HeadOffice> findById(HeadOfficeId headOfficeId) {
        Map<String, Object> filterParameters = new HashMap<>();
        filterParameters.put(HEAD_OFFICE_ID, headOfficeId.getValue());

        String sql = new StringBuilder()
                .append("SELECT ").append("HO_ID, HO_BALANCE ")
                .append("FROM ").append("HEAD_OFFICE ")
                .append("WHERE ").append("HO_ID = :").append(HEAD_OFFICE_ID).append(" ")
                .append("ORDER BY HO_ID")
                .toString();

        return executeQueryForObject(sql, filterParameters);
    }

    private Optional<HeadOffice> executeQueryForObject(String sql, Map<String, Object> filterParameters) {
        Optional result;

        try {
            result =  Optional.of(jdbcTemplate.queryForObject(sql, filterParameters, headOfficeRowMapper));
        } catch (EmptyResultDataAccessException e) {
            result = Optional.empty();
        }

        return result;
    }
}
