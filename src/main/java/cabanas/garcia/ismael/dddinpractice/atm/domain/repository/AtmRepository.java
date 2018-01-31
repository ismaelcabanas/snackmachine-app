package cabanas.garcia.ismael.dddinpractice.atm.domain.repository;

import cabanas.garcia.ismael.dddinpractice.atm.domain.model.Atm;
import cabanas.garcia.ismael.dddinpractice.atm.domain.model.AtmId;

import java.util.Optional;

public interface AtmRepository {
    void save(Atm atm);

    Optional<Atm> findById(AtmId atmId);
}
