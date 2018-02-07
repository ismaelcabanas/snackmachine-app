package cabanas.garcia.ismael.dddinpractice.atm.application;

import cabanas.garcia.ismael.dddinpractice.atm.domain.model.Atm;
import cabanas.garcia.ismael.dddinpractice.atm.domain.model.AtmId;
import cabanas.garcia.ismael.dddinpractice.atm.domain.repository.AtmHappyRepositoryStub;
import cabanas.garcia.ismael.dddinpractice.atm.domain.repository.AtmRepository;
import cabanas.garcia.ismael.dddinpractice.shared.domain.model.Money;
import cabanas.garcia.ismael.dddinpractice.shared.domain.service.EventProcessor;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

public class WithdrawMoneyServiceShould {

    //Creating new rule with recommended Strictness setting
    @Rule
    public MockitoRule rule = MockitoJUnit.rule();

    @Mock private AtmRepository atmRepositoryMock;

    @Mock private EventProcessor eventProcessorMock;

    private AtmHappyRepositoryStub atmHappyRepositoryStub;
    private Atm atm;

    @Before public void
    setUp() {
        atm = Atm.builder(new AtmId())
                .setFiveDollarCount(2)
                .setTwentyDollarCount(1)
                .build();
        atmHappyRepositoryStub = new AtmHappyRepositoryStub(atm, atmRepositoryMock);
    }

    @Test public void
    withdraw_money_from_atm() {
        WithdrawMoneyService withdrawMoneyService = new WithdrawMoneyService(atmHappyRepositoryStub, eventProcessorMock);

        withdrawMoneyService.withdraw(atm.id(), 5.00);

        verifySaveAtmWithMoney(new Money(0, 0, 0, 0, 1, 1), 5.06);
    }

    private void verifySaveAtmWithMoney(Money money, double amount) {
        atmHappyRepositoryStub.verifySaveAtmWithMoney(money, amount);
    }
}
