package cabanas.garcia.ismael.snackmachine.domain.model;

public abstract class AgreggateRoot<ID> extends Entity<ID> {
    protected AgreggateRoot(ID id) {
        super(id);
    }
}
