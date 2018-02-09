package cabanas.garcia.ismael.dddinpractice.snackmachine.application;

import cabanas.garcia.ismael.dddinpractice.snackmachine.domain.model.SnackMachine;
import cabanas.garcia.ismael.dddinpractice.snackmachine.domain.model.SnackMachineDto;
import cabanas.garcia.ismael.dddinpractice.snackmachine.domain.model.SnackMachineId;
import cabanas.garcia.ismael.dddinpractice.snackmachine.domain.model.SnackMachineNotFoundException;
import cabanas.garcia.ismael.dddinpractice.snackmachine.domain.repository.SnackMachineRepository;

public class CheckStatusOfSnackMachineService {
    private final SnackMachineRepository snackMachineRepository;

    public CheckStatusOfSnackMachineService(SnackMachineRepository snackMachineRepository) {
        this.snackMachineRepository = snackMachineRepository;
    }

    public SnackMachineDto checkStatus(SnackMachineId snackMachineId) {
        SnackMachine snackMachine =
                snackMachineRepository
                        .findById(snackMachineId).orElseThrow(() -> new SnackMachineNotFoundException(snackMachineId));

        return new SnackMachineDto(snackMachine.amountInside(), snackMachine.amountInTransaction());
    }
}
