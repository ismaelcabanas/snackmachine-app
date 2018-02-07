package cabanas.garcia.ismael.dddinpractice.management.application;

import cabanas.garcia.ismael.dddinpractice.management.domain.model.HeadOffice;
import cabanas.garcia.ismael.dddinpractice.management.domain.repository.HeadOfficeRepository;
import cabanas.garcia.ismael.dddinpractice.management.domain.repository.HeadOfficeRepositoryHappyStub;
import cabanas.garcia.ismael.dddinpractice.shared.domain.model.Money;
import cabanas.garcia.ismael.dddinpractice.snackmachine.domain.model.SnackMachine;
import cabanas.garcia.ismael.dddinpractice.snackmachine.domain.repository.SnackMachineHappyRepositoryStub;
import cabanas.garcia.ismael.dddinpractice.snackmachine.domain.repository.SnackMachineRepository;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

public class UnloadCashServiceShould {

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Mock
    private HeadOfficeRepository headOfficeRepositoryMock;

    @Mock
    private SnackMachineRepository snackMachineRepositoryMock;

    private SnackMachine snackMachine;
    private HeadOffice headOffice;
    private HeadOfficeRepositoryHappyStub headOfficeRepositoryHappyStub;
    private SnackMachineHappyRepositoryStub snackMachineHappyRepositoryStub;

    @Before public void
    setUp() {
        snackMachine = new SnackMachine();
        snackMachine.insertMoney(Money.FIVE_DOLLAR);
        snackMachine.insertMoney(Money.FIVE_DOLLAR);
        snackMachine.insertMoney(Money.TWENTY_DOLLAR);
        headOffice = new HeadOffice();
        headOfficeRepositoryHappyStub = new HeadOfficeRepositoryHappyStub(headOffice, headOfficeRepositoryMock);
        snackMachineHappyRepositoryStub = new SnackMachineHappyRepositoryStub(snackMachine, snackMachineRepositoryMock);
    }

    @Test public void
    pass_all_money_from_snackmachine_to_head_office() {
        UnloadCashService unloadCashService =
                new UnloadCashService(headOfficeRepositoryHappyStub, snackMachineHappyRepositoryStub);

        unloadCashService.unloadCash(headOffice.id(), snackMachine.id());

        verifySaveHeadOfficeWithCash(new Money(0, 0, 0, 0, 2, 1));
        verifySaveSnackMachineWithNoMoneyInside();
    }

    private void verifySaveSnackMachineWithNoMoneyInside() {
        snackMachineHappyRepositoryStub.verifySaveSnackMachineWithNoMoneyInside();
    }

    private void verifySaveHeadOfficeWithCash(Money money) {
        headOfficeRepositoryHappyStub.verifySaveHeadOfficeWithCash(money);
    }
}
