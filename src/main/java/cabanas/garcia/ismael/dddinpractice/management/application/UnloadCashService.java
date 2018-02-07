package cabanas.garcia.ismael.dddinpractice.management.application;

import cabanas.garcia.ismael.dddinpractice.management.domain.model.HeadOffice;
import cabanas.garcia.ismael.dddinpractice.management.domain.model.HeadOfficeId;
import cabanas.garcia.ismael.dddinpractice.management.domain.repository.HeadOfficeRepository;
import cabanas.garcia.ismael.dddinpractice.snackmachine.domain.model.SnackMachine;
import cabanas.garcia.ismael.dddinpractice.snackmachine.domain.model.SnackMachineId;
import cabanas.garcia.ismael.dddinpractice.snackmachine.domain.repository.SnackMachineRepository;

import java.util.Optional;

public class UnloadCashService {
    private final HeadOfficeRepository headOfficeRepository;
    private final SnackMachineRepository snackMachineRepository;

    public UnloadCashService(HeadOfficeRepository headOfficeRepository, SnackMachineRepository snackMachineRepository) {
        this.headOfficeRepository = headOfficeRepository;
        this.snackMachineRepository = snackMachineRepository;
    }

    public void unloadCash(HeadOfficeId headOfficeId, SnackMachineId snackMachineId) {
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
