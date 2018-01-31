package cabanas.garcia.ismael.dddinpractice.atm.application;

import cabanas.garcia.ismael.dddinpractice.atm.domain.model.Atm;
import cabanas.garcia.ismael.dddinpractice.atm.domain.repository.AtmRepository;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.internal.verification.VerificationModeFactory;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

public class WithdrawMoneyServiceShould {

    //Creating new rule with recommended Strictness setting
    @Rule
    public MockitoRule rule = MockitoJUnit.rule();

    @Mock private Atm atmMock;

    @Mock private AtmRepository atmRepositoryMock;

    @Test public void
    withdraw_money_from_atm() {
        WithdrawMoneyService withdrawMoneyService = new WithdrawMoneyService(atmMock, atmRepositoryMock);

        withdrawMoneyService.withdraw(1.5);

        Mockito.verify(atmRepositoryMock, VerificationModeFactory.times(1)).save(atmMock);
    }
}
