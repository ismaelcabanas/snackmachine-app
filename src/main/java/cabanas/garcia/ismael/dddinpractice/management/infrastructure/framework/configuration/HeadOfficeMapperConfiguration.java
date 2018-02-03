package cabanas.garcia.ismael.dddinpractice.management.infrastructure.framework.configuration;

import cabanas.garcia.ismael.dddinpractice.management.infrastructure.repository.HeadOfficeRowMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HeadOfficeMapperConfiguration {

    @Bean
    public HeadOfficeRowMapper headOfficeRowMapper() {
        return new HeadOfficeRowMapper();
    }
}
