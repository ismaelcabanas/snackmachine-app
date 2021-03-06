package cabanas.garcia.ismael.dddinpractice.atm.application;

import cabanas.garcia.ismael.dddinpractice.atm.domain.model.Atm;
import cabanas.garcia.ismael.dddinpractice.atm.domain.model.AtmDto;
import cabanas.garcia.ismael.dddinpractice.atm.domain.model.AtmId;
import cabanas.garcia.ismael.dddinpractice.atm.domain.model.AtmMachineNotFoundException;
import cabanas.garcia.ismael.dddinpractice.atm.domain.repository.AtmRepository;

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
