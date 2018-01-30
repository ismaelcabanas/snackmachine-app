package cabanas.garcia.ismael.snackmachine.domain.repository;

import cabanas.garcia.ismael.common.AgreggateRoot;

public abstract class BaseRepository<T extends AgreggateRoot> {

    public abstract void save(T aggregateRoot);
}
