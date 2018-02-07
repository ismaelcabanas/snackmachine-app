package cabanas.garcia.ismael.dddinpractice.atm.infrastructure.framework.configuration;

import cabanas.garcia.ismael.dddinpractice.atm.application.WithdrawMoneyService;
import cabanas.garcia.ismael.dddinpractice.atm.domain.repository.AtmRepository;
import cabanas.garcia.ismael.dddinpractice.shared.domain.service.EventProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AtmServiceConfiguration {

    @Bean
    public WithdrawMoneyService withdrawMoneyService(AtmRepository atmRepository, EventProcessor eventProcessor) {
        return new WithdrawMoneyService(atmRepository, eventProcessor);
    }
}
