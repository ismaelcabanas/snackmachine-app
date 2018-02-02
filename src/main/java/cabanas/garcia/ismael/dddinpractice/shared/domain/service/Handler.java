package cabanas.garcia.ismael.dddinpractice.shared.domain.service;

import cabanas.garcia.ismael.dddinpractice.common.DomainEvent;

public interface Handler<T extends DomainEvent> {
    void handle(T domainEvent);
}
