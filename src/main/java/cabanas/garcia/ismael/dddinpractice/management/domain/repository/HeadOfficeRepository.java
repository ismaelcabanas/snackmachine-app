package cabanas.garcia.ismael.dddinpractice.management.domain.repository;

import cabanas.garcia.ismael.dddinpractice.management.domain.model.HeadOffice;
import cabanas.garcia.ismael.dddinpractice.management.domain.model.HeadOfficeId;

import java.util.Optional;

public interface HeadOfficeRepository {
    void save(HeadOffice headOffice);

    Optional<HeadOffice> findById(HeadOfficeId headOfficeId);
}
