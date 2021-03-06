package cabanas.garcia.ismael.dddinpractice.atm.application;

import cabanas.garcia.ismael.dddinpractice.atm.domain.model.Atm;
import cabanas.garcia.ismael.dddinpractice.atm.domain.model.AtmId;
import cabanas.garcia.ismael.dddinpractice.atm.domain.repository.AtmRepository;
import cabanas.garcia.ismael.dddinpractice.shared.domain.service.EventProcessor;

import java.util.Optional;

public class WithdrawMoneyService {
    private final AtmRepository atmRepository;
    private final EventProcessor eventProcessor;

    public WithdrawMoneyService(AtmRepository atmRepository, EventProcessor eventProcessor) {
        this.atmRepository = atmRepository;
        this.eventProcessor = eventProcessor;
    }

    public void withdraw(AtmId atmId, double amount) {
        Optional<Atm> atm = atmRepository.findById(atmId);
        atm.ifPresent(am -> {
            am.withdraw(amount);
            atmRepository.save(am);
            eventProcessor.process(am.getDomainEvents());
        });
    }
}
