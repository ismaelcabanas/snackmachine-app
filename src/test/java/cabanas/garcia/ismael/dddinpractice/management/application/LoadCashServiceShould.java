package cabanas.garcia.ismael.dddinpractice.management.application;

import cabanas.garcia.ismael.dddinpractice.atm.domain.model.Atm;
import cabanas.garcia.ismael.dddinpractice.atm.domain.repository.AtmHappyRepositoryStub;
import cabanas.garcia.ismael.dddinpractice.atm.domain.repository.AtmRepository;
import cabanas.garcia.ismael.dddinpractice.management.domain.model.HeadOffice;
import cabanas.garcia.ismael.dddinpractice.management.domain.repository.HeadOfficeRepository;
import cabanas.garcia.ismael.dddinpractice.management.domain.repository.HeadOfficeRepositoryHappyStub;
import cabanas.garcia.ismael.dddinpractice.shared.domain.model.Money;
import cabanas.garcia.ismael.dddinpractice.snackmachine.domain.model.SnackMachine;
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

    @Test public void
    pass_cash_from_head_office_to_atm() {
        SnackMachine snackMachine = new SnackMachine();
        snackMachine.insertMoney(Money.FIVE_DOLLAR);
        snackMachine.insertMoney(Money.FIVE_DOLLAR);
        snackMachine.insertMoney(Money.TWENTY_DOLLAR);
        HeadOffice headOffice = new HeadOffice();
        headOffice.unloadCash(snackMachine);
        Atm atm = new Atm();
        HeadOfficeRepositoryHappyStub headOfficeRepositoryHappyStub =
                new HeadOfficeRepositoryHappyStub(headOffice, headOfficeRepositoryMock);
        AtmHappyRepositoryStub atmHappyRepositoryStub = new AtmHappyRepositoryStub(atm, atmRepositoryMock);
        LoadCashService loadCashService =
                new LoadCashService(headOffice.id(), atm.id(), headOfficeRepositoryHappyStub, atmHappyRepositoryStub);

        loadCashService.loadCash();

        headOfficeRepositoryHappyStub.verifySaveHeadOfficeWithoutCash();
        atmHappyRepositoryStub.verifySaveAtmWithMoney(new Money(0, 0, 0, 0, 2, 1));
    }
}
