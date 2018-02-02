package cabanas.garcia.ismael.dddinpractice.shared.domain.service;

import cabanas.garcia.ismael.dddinpractice.common.DomainEvent;

import java.util.List;

public interface EventProcessor {
    void process(List<DomainEvent> domainEvents);
}
