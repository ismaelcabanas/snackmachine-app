package cabanas.garcia.ismael.dddinpractice.snackmachine.application;

import cabanas.garcia.ismael.dddinpractice.shared.domain.model.Money;
import cabanas.garcia.ismael.dddinpractice.snackmachine.domain.model.SnackMachine;
import cabanas.garcia.ismael.dddinpractice.snackmachine.domain.model.Snack;
import cabanas.garcia.ismael.dddinpractice.snackmachine.domain.model.Slot;
import cabanas.garcia.ismael.dddinpractice.snackmachine.domain.model.SnackPile;
import cabanas.garcia.ismael.dddinpractice.snackmachine.domain.model.SnackMachineId;
import cabanas.garcia.ismael.dddinpractice.snackmachine.domain.model.SnackId;
import cabanas.garcia.ismael.dddinpractice.snackmachine.domain.model.SlotId;
import cabanas.garcia.ismael.dddinpractice.snackmachine.domain.repository.SnackMachineHappyRepositoryStub;
import cabanas.garcia.ismael.dddinpractice.snackmachine.domain.repository.SnackMachineRepository;
import cabanas.garcia.ismael.dddinpractice.snackmachine.domain.service.TransactionService;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.math.BigDecimal;
import java.util.function.Supplier;

public class BuySnackServiceShould {

    private static final short FIRST_POSITION = 1;

    //Creating new rule with recommended Strictness setting
    @Rule public MockitoRule rule = MockitoJUnit.rule();

    @Mock private SnackMachineRepository snackMachineRepositoryMock;

    private TransactionService transactionService = Supplier::get;

    @Test public void
    buy_a_snack() {
        SnackMachine snackMachine = SnackMachine.builder(new SnackMachineId())
                .setFiveDollarCount(2)
                .setTwentyDollarCount(1)
                .setSlotOne(getSlotOne())
                .build();
        snackMachine.insertMoney(Money.DOLLAR);
        SnackMachineHappyRepositoryStub snackMachineHappyRepositoryStub =
                new SnackMachineHappyRepositoryStub(snackMachine, snackMachineRepositoryMock);
        BuySnackService buySnackService = new BuySnackService(snackMachineHappyRepositoryStub, transactionService);

        buySnackService.buySnack(snackMachine.id(), FIRST_POSITION);

        snackMachineHappyRepositoryStub.verifySaveSnackMachineWithSlotsWithMoney(new Money(0, 0, 0, 1, 2, 1));
    }

    private Slot getSlotOne() {
        return Slot.builder().setId(new SlotId())
                .setPosition(FIRST_POSITION)
                .setSnackPile(SnackPile.builder()
                        .setPrice(BigDecimal.ONE)
                        .setQuantity(10)
                        .setSnack(Snack.builder(new SnackId()).build())
                        .build())
                .build();
    }

}
