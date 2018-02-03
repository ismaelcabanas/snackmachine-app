package cabanas.garcia.ismael.dddinpractice.shared.domain.service;

import cabanas.garcia.ismael.dddinpractice.common.DomainEvent;

import java.lang.reflect.ParameterizedType;
import java.util.List;

public class DefaultEventDispatcher implements EventDispatcher {

    private List<Handler> handlers;

    public DefaultEventDispatcher(List<Handler> handlers) {
        this.handlers = handlers;
    }

    @Override
    public void dispatchEvent(DomainEvent domainEvent) {
        handlers.forEach(handler -> {
            boolean canHandleEvent =
                    ((ParameterizedType) handler.getClass().getGenericInterfaces()[0])
                        .getActualTypeArguments()[0].getTypeName().equals(domainEvent.getClass().getTypeName());

            if (canHandleEvent) {
                handler.handle(domainEvent);
            }
        });
    }
}
