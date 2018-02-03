package cabanas.garcia.ismael.dddinpractice.common;

import java.util.ArrayList;
import java.util.List;

public abstract class AgreggateRoot<ID> extends Entity<ID> {

    protected List<DomainEvent> domainEvents = new ArrayList<>();

    protected AgreggateRoot(ID id) {
        super(id);
    }

    protected void registerEvent(DomainEvent domainEvent) {
        domainEvents.add(domainEvent);
    }

    public List<DomainEvent> getDomainEvents() {
        return domainEvents;
    }
}
