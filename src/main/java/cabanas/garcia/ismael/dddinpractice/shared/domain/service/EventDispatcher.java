package cabanas.garcia.ismael.dddinpractice.shared.domain.service;

import cabanas.garcia.ismael.dddinpractice.common.DomainEvent;

public interface EventDispatcher {
    void dispatchEvent(DomainEvent domainEvent);
}
