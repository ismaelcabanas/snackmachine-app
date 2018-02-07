package cabanas.garcia.ismael.dddinpractice.management.application;

import cabanas.garcia.ismael.dddinpractice.atm.domain.model.Atm;
import cabanas.garcia.ismael.dddinpractice.atm.domain.repository.AtmHappyRepositoryStub;
import cabanas.garcia.ismael.dddinpractice.atm.domain.repository.AtmRepository;
import cabanas.garcia.ismael.dddinpractice.management.domain.model.HeadOffice;
import cabanas.garcia.ismael.dddinpractice.management.domain.repository.HeadOfficeRepository;
import cabanas.garcia.ismael.dddinpractice.management.domain.repository.HeadOfficeRepositoryHappyStub;
import cabanas.garcia.ismael.dddinpractice.shared.domain.model.Money;
import cabanas.garcia.ismael.dddinpractice.snackmachine.domain.model.SnackMachine;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

public class LoadCashServiceShould {

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Mock
    private HeadOfficeRepository headOfficeRepositoryMock;

    @Mock
    private AtmRepository atmRepositoryMock;

    private HeadOfficeRepositoryHappyStub headOfficeRepositoryHappyStub;
    private AtmHappyRepositoryStub atmHappyRepositoryStub;
    private HeadOffice headOffice;
    private Atm atm;

    @Before public void
    setUp() {
        SnackMachine snackMachine = new SnackMachine();
        snackMachine.insertMoney(Money.FIVE_DOLLAR);
        snackMachine.insertMoney(Money.FIVE_DOLLAR);
        snackMachine.insertMoney(Money.TWENTY_DOLLAR);
        headOffice = new HeadOffice();
        headOffice.unloadCash(snackMachine);
        atm = new Atm();
        headOfficeRepositoryHappyStub = new HeadOfficeRepositoryHappyStub(headOffice, headOfficeRepositoryMock);
        atmHappyRepositoryStub = new AtmHappyRepositoryStub(atm, atmRepositoryMock);
    }

    @Test public void
    pass_cash_from_head_office_to_atm() {
        LoadCashService loadCashService =
                new LoadCashService(headOfficeRepositoryHappyStub, atmHappyRepositoryStub);

        loadCashService.loadCash(headOffice.id(), atm.id());

        verifySaveHeadOfficeWithoutCash();
        verifySaveAtmWithMoney(new Money(0, 0, 0, 0, 2, 1));
    }

    private void verifySaveAtmWithMoney(Money money) {
        atmHappyRepositoryStub.verifySaveAtmWithMoney(money);
    }

    private void verifySaveHeadOfficeWithoutCash() {
        headOfficeRepositoryHappyStub.verifySaveHeadOfficeWithoutCash();
    }
}
