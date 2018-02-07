package cabanas.garcia.ismael.dddinpractice.management.infrastructure.framework.configuration;

import cabanas.garcia.ismael.dddinpractice.atm.domain.repository.AtmRepository;
import cabanas.garcia.ismael.dddinpractice.management.application.LoadCashService;
import cabanas.garcia.ismael.dddinpractice.management.application.UnloadCashService;
import cabanas.garcia.ismael.dddinpractice.management.domain.repository.HeadOfficeRepository;
import cabanas.garcia.ismael.dddinpractice.snackmachine.domain.repository.SnackMachineRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HeadOfficeServiceConfiguration {

    @Bean
    public LoadCashService loadCashService(HeadOfficeRepository headOfficeRepository, AtmRepository atmRepository) {
        return new LoadCashService(headOfficeRepository, atmRepository);
    }

    @Bean
    public UnloadCashService unloadCashService(HeadOfficeRepository headOfficeRepository, SnackMachineRepository snackMachineRepository) {
        return new UnloadCashService(headOfficeRepository, snackMachineRepository);
    }
}
