package cabanas.garcia.ismael.dddinpractice.snackmachine.domain.repository;

import cabanas.garcia.ismael.dddinpractice.common.AgreggateRoot;
import cabanas.garcia.ismael.dddinpractice.common.ValueObject;

import java.util.Optional;

public interface BaseRepository<ID extends ValueObject<ID>, T extends AgreggateRoot> {

    Optional<T> findById(ID id);
    void save(T aggregateRoot);
}
