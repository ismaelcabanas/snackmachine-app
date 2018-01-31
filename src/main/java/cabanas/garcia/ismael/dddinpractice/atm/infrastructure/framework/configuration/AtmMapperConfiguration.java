package cabanas.garcia.ismael.dddinpractice.atm.infrastructure.framework.configuration;

import cabanas.garcia.ismael.dddinpractice.atm.infrastructure.repository.AtmRowMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AtmMapperConfiguration {

    @Bean
    public AtmRowMapper atmRowMapper() {
        return new AtmRowMapper();
    }
}
