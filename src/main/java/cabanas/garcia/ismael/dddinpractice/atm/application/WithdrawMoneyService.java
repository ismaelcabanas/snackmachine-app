package cabanas.garcia.ismael.dddinpractice.atm.application;

import cabanas.garcia.ismael.dddinpractice.atm.domain.model.Atm;
import cabanas.garcia.ismael.dddinpractice.atm.domain.repository.AtmRepository;
import cabanas.garcia.ismael.dddinpractice.shared.domain.service.EventProcessor;

public class WithdrawMoneyService {
    private final Atm atm;
    private final AtmRepository atmRepository;
    private final EventProcessor eventProcessor;

    public WithdrawMoneyService(Atm atm, AtmRepository atmRepository, EventProcessor eventProcessor) {
        this.atm = atm;
        this.atmRepository = atmRepository;
        this.eventProcessor = eventProcessor;
    }

    public void withdraw(double amount) {
        atm.withdraw(amount);
        atmRepository.save(atm);
        eventProcessor.process(atm.getDomainEvents());
    }
}
