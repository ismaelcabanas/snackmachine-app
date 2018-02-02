package cabanas.garcia.ismael.dddinpractice.management.domain.model;

import cabanas.garcia.ismael.dddinpractice.atm.domain.model.BalanceChargedEvent;
import cabanas.garcia.ismael.dddinpractice.management.domain.repository.HeadOfficeRepository;
import cabanas.garcia.ismael.dddinpractice.management.domain.repository.HeadOfficeRepositoryHappyStub;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

public class BalanceChargedHandlerShould {

    //Creating new rule with recommended Strictness setting
    @Rule public MockitoRule rule = MockitoJUnit.rule();

    @Mock private HeadOfficeRepository headOfficeRepository;

    @Test public void
    update_the_balance_of_head_office() {
        HeadOffice headOffice = new HeadOffice();
        HeadOfficeRepositoryHappyStub headOfficeRepositoryStub = new HeadOfficeRepositoryHappyStub(headOffice, headOfficeRepository);
        BalanceChargedHandler balanceChargedHandler = new BalanceChargedHandler(headOfficeRepositoryStub);
        double amountCharged = 2.00;
        BalanceChargedEvent balanceChargedEvent = new BalanceChargedEvent(amountCharged);

        balanceChargedHandler.handle(balanceChargedEvent);

        headOfficeRepositoryStub.verifyUpdateBalanceOfHeadOffice(amountCharged);
    }

}
