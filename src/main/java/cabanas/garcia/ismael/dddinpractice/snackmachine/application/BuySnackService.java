package cabanas.garcia.ismael.dddinpractice.snackmachine.application;

import cabanas.garcia.ismael.dddinpractice.snackmachine.domain.model.SnackMachine;
import cabanas.garcia.ismael.dddinpractice.snackmachine.domain.model.SnackMachineId;
import cabanas.garcia.ismael.dddinpractice.snackmachine.domain.repository.SnackMachineRepository;
import cabanas.garcia.ismael.dddinpractice.snackmachine.domain.service.TransactionService;

import java.util.Optional;

public class BuySnackService {

    private final SnackMachineRepository snackMachineRepository;
    private final TransactionService transactionService;

    public BuySnackService(SnackMachineRepository snackMachineRepository,
                           TransactionService transactionService) {
        this.snackMachineRepository = snackMachineRepository;
        this.transactionService = transactionService;
    }

    public void buySnack(SnackMachineId snackMachineId, short position) {
        Optional<SnackMachine> snackMachine = snackMachineRepository.findById(snackMachineId);
        snackMachine.ifPresent(sm -> {
            transactionService.doInTransaction(() -> {
                sm.buySnack(position);
                snackMachineRepository.saveWithSlots(sm);
                return snackMachineId;
            });

        });

    }
}
