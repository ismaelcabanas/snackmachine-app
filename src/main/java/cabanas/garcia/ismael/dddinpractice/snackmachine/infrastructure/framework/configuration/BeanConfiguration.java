package cabanas.garcia.ismael.dddinpractice.snackmachine.infrastructure.framework.configuration;

import cabanas.garcia.ismael.dddinpractice.snackmachine.domain.model.SnackMachine;
import cabanas.garcia.ismael.dddinpractice.snackmachine.domain.repository.SnackMachineRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

    @Bean
    public SnackMachine snackMachine(SnackMachineRepository snackMachineRepository) {
        return snackMachineRepository.findById("1").orElseThrow(RuntimeException::new);
    }
}
