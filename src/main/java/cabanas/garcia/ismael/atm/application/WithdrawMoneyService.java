package cabanas.garcia.ismael.atm.application;

import cabanas.garcia.ismael.atm.domain.model.Atm;
import cabanas.garcia.ismael.atm.domain.repository.AtmRepository;

public class WithdrawMoneyService {
    private final Atm atm;
    private final AtmRepository atmRepository;

    public WithdrawMoneyService(Atm atm, AtmRepository atmRepository) {
        this.atm = atm;
        this.atmRepository = atmRepository;
    }

    public void withdraw(double amount) {
        atm.withdraw(amount);
        atmRepository.save(atm);
    }
}