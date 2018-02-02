package cabanas.garcia.ismael.dddinpractice.shared.domain.service;

import cabanas.garcia.ismael.dddinpractice.common.DomainEvent;

import java.util.List;

public class DefaultEventProcessor implements EventProcessor {

    private final EventDispatcher eventDispatcher;

    public DefaultEventProcessor(EventDispatcher eventDispatcher) {
        this.eventDispatcher = eventDispatcher;
    }

    @Override
    public void process(List<DomainEvent> domainEvents) {
        domainEvents.forEach(domainEvent -> eventDispatcher.dispatchEvent(domainEvent));
    }
}
