package cabanas.garcia.ismael.dddinpractice.management.infrastructure.framework.configuration;

import cabanas.garcia.ismael.dddinpractice.management.domain.repository.HeadOfficeRepository;
import cabanas.garcia.ismael.dddinpractice.management.infrastructure.repository.HeadOfficeRowMapper;
import cabanas.garcia.ismael.dddinpractice.management.infrastructure.repository.PostgresHeadOfficeRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

@Configuration
public class HeadOfficeRepositoryConfiguration {
    @Bean
    public HeadOfficeRepository headOfficeRepository(NamedParameterJdbcTemplate jdbcTemplate,
                                                     HeadOfficeRowMapper headOfficeRowMapper) {
        return new PostgresHeadOfficeRepository(jdbcTemplate, headOfficeRowMapper);
    }
}
