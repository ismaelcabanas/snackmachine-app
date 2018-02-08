package cabanas.garcia.ismael.dddinpractice.management.application;

import cabanas.garcia.ismael.dddinpractice.management.domain.model.HeadOffice;
import cabanas.garcia.ismael.dddinpractice.management.domain.model.HeadOfficeDto;
import cabanas.garcia.ismael.dddinpractice.management.domain.model.HeadOfficeId;
import cabanas.garcia.ismael.dddinpractice.management.domain.model.HeadOfficeNotFoundException;
import cabanas.garcia.ismael.dddinpractice.management.domain.repository.HeadOfficeRepository;

public class CheckStatusHeadOfficeService {

    private final HeadOfficeRepository headOfficeRepository;

    public CheckStatusHeadOfficeService(HeadOfficeRepository headOfficeRepository) {
        this.headOfficeRepository = headOfficeRepository;
    }


    public HeadOfficeDto checkStatus(HeadOfficeId headOfficeId) {
        HeadOffice headOffice = headOfficeRepository.findById(headOfficeId).orElseThrow(() -> new HeadOfficeNotFoundException(headOfficeId));
        return new HeadOfficeDto(headOffice.cash().amount(), headOffice.balance());
    }
}
