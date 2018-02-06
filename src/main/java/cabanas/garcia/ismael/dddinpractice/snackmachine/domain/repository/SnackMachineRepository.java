package cabanas.garcia.ismael.dddinpractice.snackmachine.domain.repository;

import cabanas.garcia.ismael.dddinpractice.shared.domain.repository.BaseRepository;
import cabanas.garcia.ismael.dddinpractice.snackmachine.domain.model.SnackMachine;
import cabanas.garcia.ismael.dddinpractice.snackmachine.domain.model.SnackMachineId;

public interface SnackMachineRepository extends BaseRepository<SnackMachineId, SnackMachine> {
    void saveWithSlots(SnackMachine snackMachine);
}
