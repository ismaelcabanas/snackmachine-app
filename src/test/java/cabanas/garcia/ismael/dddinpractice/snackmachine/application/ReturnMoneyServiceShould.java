package cabanas.garcia.ismael.dddinpractice.snackmachine.application;

import cabanas.garcia.ismael.dddinpractice.shared.domain.model.Money;
import cabanas.garcia.ismael.dddinpractice.snackmachine.domain.model.SnackMachine;
import cabanas.garcia.ismael.dddinpractice.snackmachine.domain.model.SnackMachineId;
import cabanas.garcia.ismael.dddinpractice.snackmachine.domain.repository.SnackMachineHappyRepositoryStub;
import cabanas.garcia.ismael.dddinpractice.snackmachine.domain.repository.SnackMachineRepository;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

public class ReturnMoneyServiceShould {
    //Creating new rule with recommended Strictness setting
    @Rule
    public MockitoRule rule = MockitoJUnit.rule();

    @Mock
    private SnackMachineRepository snackMachineRepositoryMock;

    @Test
    public void
    return_money_inserted_into_snack_machine() {
        SnackMachine snackMachine = SnackMachine.builder(new SnackMachineId())
                .setFiveDollarCount(2)
                .setTwentyDollarCount(1)
                .build();
        snackMachine.insertMoney(Money.DOLLAR);
        SnackMachineHappyRepositoryStub snackMachineHappyRepositoryStub =
                new SnackMachineHappyRepositoryStub(snackMachine, snackMachineRepositoryMock);
        ReturnMoneyService returnMoneyService = new ReturnMoneyService(snackMachineHappyRepositoryStub);

        returnMoneyService.returnMoney(snackMachine.id());

        snackMachineHappyRepositoryStub.verifySaveSnackMachineWithMoney(new Money(0, 0, 0, 0, 2, 1));
    }
}
