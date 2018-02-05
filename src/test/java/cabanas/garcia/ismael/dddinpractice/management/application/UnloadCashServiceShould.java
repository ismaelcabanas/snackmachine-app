package cabanas.garcia.ismael.dddinpractice.management.application;

import cabanas.garcia.ismael.dddinpractice.management.domain.model.HeadOffice;
import cabanas.garcia.ismael.dddinpractice.management.domain.repository.HeadOfficeRepository;
import cabanas.garcia.ismael.dddinpractice.management.domain.repository.HeadOfficeRepositoryHappyStub;
import cabanas.garcia.ismael.dddinpractice.shared.domain.model.Money;
import cabanas.garcia.ismael.dddinpractice.snackmachine.domain.model.SnackMachine;
import cabanas.garcia.ismael.dddinpractice.snackmachine.domain.repository.SnackMachineHappyRepositoryStub;
import cabanas.garcia.ismael.dddinpractice.snackmachine.domain.repository.SnackMachineRepository;
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

    @Test public void
    pass_all_money_from_snackmachine_to_head_office() {
        HeadOffice headOffice = new HeadOffice();
        SnackMachine snackMachine = new SnackMachine();
        snackMachine.insertMoney(Money.FIVE_DOLLAR);
        snackMachine.insertMoney(Money.FIVE_DOLLAR);
        snackMachine.insertMoney(Money.TWENTY_DOLLAR);
        HeadOfficeRepositoryHappyStub headOfficeRepositoryHappyStub =
                new HeadOfficeRepositoryHappyStub(headOffice, headOfficeRepositoryMock);
        SnackMachineHappyRepositoryStub snackMachineHappyRepositoryStub =
                new SnackMachineHappyRepositoryStub(snackMachine, snackMachineRepositoryMock);
        UnloadCashService unloadCashService =
                new UnloadCashService(headOffice.id(), snackMachine.id(), headOfficeRepositoryHappyStub, snackMachineHappyRepositoryStub);

        unloadCashService.unload();

        headOfficeRepositoryHappyStub.verifySaveHeadOfficeWithCash(new Money(0, 0, 0, 0, 2, 1));
        snackMachineHappyRepositoryStub.verifySaveSnackMachineWithNoMoneyInside();
    }
}
