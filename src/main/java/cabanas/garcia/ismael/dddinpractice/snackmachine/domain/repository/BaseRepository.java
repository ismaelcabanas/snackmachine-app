package cabanas.garcia.ismael.dddinpractice.snackmachine.domain.repository;

import cabanas.garcia.ismael.dddinpractice.common.AgreggateRoot;

public abstract class BaseRepository<T extends AgreggateRoot> {

    public abstract void save(T aggregateRoot);
}
