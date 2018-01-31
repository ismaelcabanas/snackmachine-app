package cabanas.garcia.ismael.dddinpractice.snackmachine.infrastructure.framework.configuration;

import cabanas.garcia.ismael.dddinpractice.snackmachine.infrastructure.repository.SnackMachineResultSetExtractor;
import cabanas.garcia.ismael.dddinpractice.snackmachine.infrastructure.repository.SnackMachineRowMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfiguration {

    @Bean
    public SnackMachineResultSetExtractor snackMachineResultSetExtractor() {
        return new SnackMachineResultSetExtractor();
    }

    @Bean
    public SnackMachineRowMapper snackMachineRowMapper(SnackMachineResultSetExtractor snackMachineResultSetExtractor) {
        return new SnackMachineRowMapper(snackMachineResultSetExtractor);
    }
}
