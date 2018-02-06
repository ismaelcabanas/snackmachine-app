package cabanas.garcia.ismael.dddinpractice.management.application;

import cabanas.garcia.ismael.dddinpractice.management.domain.model.HeadOffice;
import cabanas.garcia.ismael.dddinpractice.management.domain.model.HeadOfficeId;
import cabanas.garcia.ismael.dddinpractice.management.domain.repository.HeadOfficeRepository;
import cabanas.garcia.ismael.dddinpractice.snackmachine.domain.model.SnackMachine;
import cabanas.garcia.ismael.dddinpractice.snackmachine.domain.model.SnackMachineId;
import cabanas.garcia.ismael.dddinpractice.snackmachine.domain.repository.SnackMachineRepository;

import java.util.Optional;

public class UnloadCashService {
    private final HeadOfficeId headOfficeId;
    private final SnackMachineId snackMachineId;
    private final HeadOfficeRepository headOfficeRepository;
    private final SnackMachineRepository snackMachineRepository;

    public UnloadCashService(HeadOfficeId headOfficeId, SnackMachineId snackMachineId,
                             HeadOfficeRepository headOfficeRepository, SnackMachineRepository snackMachineRepository) {
        this.headOfficeId = headOfficeId;
        this.snackMachineId = snackMachineId;
        this.headOfficeRepository = headOfficeRepository;
        this.snackMachineRepository = snackMachineRepository;
    }

    public void unload() {
        Optional<HeadOffice> headOffice = headOfficeRepository.findById(headOfficeId);
        Optional<SnackMachine> snackMachine = snackMachineRepository.findById(snackMachineId);
        headOffice.ifPresent(ho -> {
            snackMachine.ifPresent(sm -> {
                ho.unloadCash(sm);
                headOfficeRepository.save(ho);
                snackMachineRepository.save(sm);
            });
        });
    }
}
