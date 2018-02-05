package cabanas.garcia.ismael.dddinpractice.atm.application;

import cabanas.garcia.ismael.dddinpractice.atm.domain.model.Atm;
import cabanas.garcia.ismael.dddinpractice.atm.domain.model.AtmId;
import cabanas.garcia.ismael.dddinpractice.atm.domain.repository.AtmHappyRepositoryStub;
import cabanas.garcia.ismael.dddinpractice.atm.domain.repository.AtmRepository;
import cabanas.garcia.ismael.dddinpractice.shared.domain.model.Money;
import cabanas.garcia.ismael.dddinpractice.shared.domain.service.EventProcessor;
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

    @Mock private EventProcessor eventProcessorMock;

    @Test public void
    withdraw_money_from_atm() {
        Atm atm = Atm.builder(new AtmId())
                .setFiveDollarCount(2)
                .setTwentyDollarCount(1)
                .build();
        AtmHappyRepositoryStub atmHappyRepositoryStub = new AtmHappyRepositoryStub(atm, atmRepositoryMock);
        WithdrawMoneyService withdrawMoneyService = new WithdrawMoneyService(atm.id(), atmHappyRepositoryStub, eventProcessorMock);

        withdrawMoneyService.withdraw(5.00);

        atmHappyRepositoryStub.verifySaveAtmWithMoney(new Money(0, 0, 0, 0, 1, 1), 5.06);
    }
}
