package cabanas.garcia.ismael.dddinpractice.atm.infrastructure.framework.configuration;

import cabanas.garcia.ismael.dddinpractice.atm.domain.repository.AtmRepository;
import cabanas.garcia.ismael.dddinpractice.atm.infrastructure.repository.AtmRowMapper;
import cabanas.garcia.ismael.dddinpractice.atm.infrastructure.repository.PostgresAtmRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

@Configuration
public class AtmRepositoryConfiguration {
    @Bean
    public AtmRepository atmRepository(NamedParameterJdbcTemplate jdbcTemplate, AtmRowMapper atmRowMapper) {
        return new PostgresAtmRepository(jdbcTemplate, atmRowMapper);
    }
}
