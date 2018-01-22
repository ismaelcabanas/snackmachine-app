package cabanas.garcia.ismael.snackmachine.domain.repository;

import cabanas.garcia.ismael.snackmachine.domain.model.SnackMachine;

import java.util.Optional;

public interface SnackMachineRepository {
    void save(SnackMachine snackMachine);

    Optional<SnackMachine> getById(String snackMachineId);
}
