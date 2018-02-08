package cabanas.garcia.ismael.dddinpractice.atm.application;

import cabanas.garcia.ismael.dddinpractice.atm.domain.model.Atm;
import cabanas.garcia.ismael.dddinpractice.atm.domain.model.AtmDto;
import cabanas.garcia.ismael.dddinpractice.atm.domain.model.AtmId;
import cabanas.garcia.ismael.dddinpractice.atm.domain.model.AtmMachineNotFoundException;
import cabanas.garcia.ismael.dddinpractice.atm.domain.repository.AtmRepository;
import cabanas.garcia.ismael.dddinpractice.management.domain.model.HeadOffice;
import cabanas.garcia.ismael.dddinpractice.management.domain.model.HeadOfficeDto;
import cabanas.garcia.ismael.dddinpractice.management.domain.model.HeadOfficeId;
import cabanas.garcia.ismael.dddinpractice.management.domain.model.HeadOfficeNotFoundException;

public class CheckStatusATMMachineService {

    private final AtmRepository atmRepository;

    public CheckStatusATMMachineService(AtmRepository atmRepository) {
        this.atmRepository = atmRepository;
    }


    public AtmDto checkStatus(AtmId atmId) {
        Atm atm = atmRepository.findById(atmId).orElseThrow(() -> new AtmMachineNotFoundException(atmId));
        return new AtmDto(atm.moneyInside().amount());
    }
}
