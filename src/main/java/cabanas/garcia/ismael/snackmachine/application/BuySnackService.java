package cabanas.garcia.ismael.snackmachine.application;

import cabanas.garcia.ismael.snackmachine.domain.model.SnackMachine;
import cabanas.garcia.ismael.snackmachine.domain.repository.SnackMachineRepository;
import cabanas.garcia.ismael.snackmachine.domain.service.TransactionService;

public class BuySnackService {

    private final SnackMachine snackMachine;
    private final SnackMachineRepository snackMachineRepository;
    private final TransactionService transactionService;

    public BuySnackService(SnackMachine snackMachine, SnackMachineRepository snackMachineRepository,
                           TransactionService transactionService) {
        this.snackMachine = snackMachine;
        this.snackMachineRepository = snackMachineRepository;
        this.transactionService = transactionService;
    }

    public void buySnack(short position) {
        transactionService.doInTransaction(() -> {
            snackMachine.buySnack(position);
            snackMachineRepository.save(snackMachine);
            return snackMachine;
        });

    }
}
