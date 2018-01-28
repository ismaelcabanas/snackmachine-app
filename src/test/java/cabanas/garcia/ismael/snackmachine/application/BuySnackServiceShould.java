package cabanas.garcia.ismael.snackmachine.application;

import cabanas.garcia.ismael.snackmachine.domain.model.SnackMachine;
import cabanas.garcia.ismael.snackmachine.domain.repository.SnackMachineRepository;
import cabanas.garcia.ismael.snackmachine.domain.service.TransactionService;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.util.function.Supplier;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.internal.verification.VerificationModeFactory.times;

public class BuySnackServiceShould {

    private static final short FIRST_POSITION = 1;

    //Creating new rule with recommended Strictness setting
    @Rule public MockitoRule rule = MockitoJUnit.rule();

    @Mock private SnackMachine snackMachine;

    @Mock private SnackMachineRepository snackMachineRepository;

    private TransactionService transactionService = Supplier::get;

    @Test
    public void buy_a_snack() {
        BuySnackService buySnackService = new BuySnackService(snackMachine, snackMachineRepository, transactionService);

        buySnackService.buySnack(FIRST_POSITION);

        verify(snackMachine, times(1)).buySnack(FIRST_POSITION);
        verify(snackMachineRepository, times(1)).save(any());
    }
}
