package cabanas.garcia.ismael.atm.domain.repository;

import cabanas.garcia.ismael.atm.domain.model.Atm;
import cabanas.garcia.ismael.atm.domain.model.AtmId;

import java.util.Optional;

public interface AtmRepository {
    void save(Atm atm);

    Optional<Atm> findById(AtmId atmId);
}
