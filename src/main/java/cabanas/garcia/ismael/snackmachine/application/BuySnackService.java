package cabanas.garcia.ismael.snackmachine.application;

import cabanas.garcia.ismael.snackmachine.domain.model.SnackMachine;
import cabanas.garcia.ismael.snackmachine.domain.repository.SnackMachineRepository;

public class BuySnackService {

    private SnackMachine snackMachine;
    private SnackMachineRepository snackMachineRepository;

    public void buySnack(short position) {
        snackMachine.buySnack(position);
        snackMachineRepository.save(snackMachine);
    }
}
