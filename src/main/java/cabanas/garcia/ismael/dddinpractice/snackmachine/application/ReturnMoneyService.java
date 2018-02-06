package cabanas.garcia.ismael.dddinpractice.snackmachine.application;

import cabanas.garcia.ismael.dddinpractice.snackmachine.domain.model.SnackMachine;
import cabanas.garcia.ismael.dddinpractice.snackmachine.domain.model.SnackMachineId;
import cabanas.garcia.ismael.dddinpractice.snackmachine.domain.repository.SnackMachineRepository;

import java.util.Optional;

public class ReturnMoneyService {
    private final SnackMachineRepository snackMachineRepository;

    public ReturnMoneyService(SnackMachineRepository snackMachineRepository) {
        this.snackMachineRepository = snackMachineRepository;
    }

    public void returnMoney(SnackMachineId snackMachineId) {
        Optional<SnackMachine> snackMachine = snackMachineRepository.findById(snackMachineId);
        snackMachine.ifPresent(sm -> {
            sm.returnMoney();
            snackMachineRepository.save(sm);
        });
    }
}
