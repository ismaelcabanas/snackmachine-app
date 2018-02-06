package cabanas.garcia.ismael.dddinpractice.snackmachine.application;

import cabanas.garcia.ismael.dddinpractice.shared.domain.model.Money;
import cabanas.garcia.ismael.dddinpractice.snackmachine.domain.model.SnackMachine;
import cabanas.garcia.ismael.dddinpractice.snackmachine.domain.model.SnackMachineId;
import cabanas.garcia.ismael.dddinpractice.snackmachine.domain.repository.SnackMachineRepository;

import java.util.Optional;

public class InsertMoneyService {

    private final SnackMachineRepository snackMachineRepository;

    public InsertMoneyService(SnackMachineRepository snackMachineRepository) {
        this.snackMachineRepository = snackMachineRepository;
    }

    public void insertMoney(SnackMachineId snackMachineId, Money money) {
        Optional<SnackMachine> snackMachine = snackMachineRepository.findById(snackMachineId);
        snackMachine.ifPresent(sm -> {
            sm.insertMoney(money);
            snackMachineRepository.save(sm);
        });
    }
}
