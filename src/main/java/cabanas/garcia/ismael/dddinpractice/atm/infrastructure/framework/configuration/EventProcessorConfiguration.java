package cabanas.garcia.ismael.dddinpractice.atm.infrastructure.framework.configuration;

import cabanas.garcia.ismael.dddinpractice.management.domain.model.BalanceChargedHandler;
import cabanas.garcia.ismael.dddinpractice.management.domain.repository.HeadOfficeRepository;
import cabanas.garcia.ismael.dddinpractice.shared.domain.service.DefaultEventDispatcher;
import cabanas.garcia.ismael.dddinpractice.shared.domain.service.DefaultEventProcessor;
import cabanas.garcia.ismael.dddinpractice.shared.domain.service.EventProcessor;
import cabanas.garcia.ismael.dddinpractice.shared.domain.service.Handler;
import cabanas.garcia.ismael.dddinpractice.shared.domain.service.EventDispatcher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;

@Configuration
public class EventProcessorConfiguration {
    @Bean
    public List<Handler> handlers(HeadOfficeRepository headOfficeRepository) {
        return Arrays.asList(new BalanceChargedHandler(headOfficeRepository));
    }

    @Bean
    public EventDispatcher eventDispatcher(List<Handler> handlers) {
        return new DefaultEventDispatcher(handlers);
    }

    @Bean
    public EventProcessor eventProcessor(EventDispatcher eventDispatcher) {
        return new DefaultEventProcessor(eventDispatcher);
    }
}
