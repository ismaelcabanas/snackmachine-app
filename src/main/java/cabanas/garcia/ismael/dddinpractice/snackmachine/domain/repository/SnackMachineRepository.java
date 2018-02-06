package cabanas.garcia.ismael.dddinpractice.snackmachine.domain.repository;

import cabanas.garcia.ismael.dddinpractice.snackmachine.domain.model.SnackMachine;

import java.util.Optional;

public interface SnackMachineRepository {
    void save(SnackMachine snackMachine);

    Optional<SnackMachine> findById(String snackMachineId);

    void saveWithSlots(SnackMachine snackMachine);
}
