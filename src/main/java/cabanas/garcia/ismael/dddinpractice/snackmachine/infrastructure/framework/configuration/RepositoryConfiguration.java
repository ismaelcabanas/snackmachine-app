package cabanas.garcia.ismael.dddinpractice.snackmachine.infrastructure.framework.configuration;

import cabanas.garcia.ismael.dddinpractice.snackmachine.domain.repository.SnackMachineRepository;
import cabanas.garcia.ismael.dddinpractice.snackmachine.infrastructure.repository.PostgresSnackMachineRepository;
import cabanas.garcia.ismael.dddinpractice.snackmachine.infrastructure.repository.SnackMachineRowMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

@Configuration
public class RepositoryConfiguration {

    @Bean
    public SnackMachineRepository snackMachineRepository(NamedParameterJdbcTemplate jdbcTemplate,
                                                         SnackMachineRowMapper snackMachineRowMapper) {
        return new PostgresSnackMachineRepository(jdbcTemplate, snackMachineRowMapper);
    }
}
