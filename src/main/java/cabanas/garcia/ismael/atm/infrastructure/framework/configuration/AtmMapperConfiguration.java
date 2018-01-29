package cabanas.garcia.ismael.atm.infrastructure.framework.configuration;

import cabanas.garcia.ismael.atm.infrastructure.repository.AtmRowMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AtmMapperConfiguration {

    @Bean
    public AtmRowMapper atmRowMapper() {
        return new AtmRowMapper();
    }
}
