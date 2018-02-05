package cabanas.garcia.ismael.dddinpractice.management.application;

import cabanas.garcia.ismael.dddinpractice.atm.domain.model.Atm;
import cabanas.garcia.ismael.dddinpractice.atm.domain.model.AtmId;
import cabanas.garcia.ismael.dddinpractice.atm.domain.repository.AtmRepository;
import cabanas.garcia.ismael.dddinpractice.management.domain.model.HeadOffice;
import cabanas.garcia.ismael.dddinpractice.management.domain.model.HeadOfficeId;
import cabanas.garcia.ismael.dddinpractice.management.domain.repository.HeadOfficeRepository;

import java.util.Optional;

public class LoadCashService {

    private final HeadOfficeId headOfficeId;
    private final AtmId atmId;
    private final HeadOfficeRepository headOfficeRepository;
    private final AtmRepository atmRepository;

    public LoadCashService(HeadOfficeId headOfficeId, AtmId atmId, HeadOfficeRepository headOfficeRepository, AtmRepository atmRepository) {
        this.headOfficeId = headOfficeId;
        this.atmId = atmId;
        this.headOfficeRepository = headOfficeRepository;
        this.atmRepository = atmRepository;
    }

    public void loadCash() {
        Optional<HeadOffice> headOffice = headOfficeRepository.findById(headOfficeId);
        Optional<Atm> atm = atmRepository.findById(atmId);

        headOffice.ifPresent(ho -> {
            atm.ifPresent(am -> {
                ho.loadCash(am);
                headOfficeRepository.save(ho);
                atmRepository.save(am);
            });
        });
    }
}
