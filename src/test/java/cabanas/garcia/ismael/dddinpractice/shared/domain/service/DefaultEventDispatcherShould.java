package cabanas.garcia.ismael.dddinpractice.shared.domain.service;

import cabanas.garcia.ismael.dddinpractice.atm.domain.model.BalanceChargedEvent;
import cabanas.garcia.ismael.dddinpractice.management.domain.model.BalanceChargedHandler;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.internal.verification.VerificationModeFactory;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class DefaultEventDispatcherShould {

    @Test public void
    dispatch_an_event() {
        List<Handler> handlers = new ArrayList<>();
        BalanceChargedHandler handler = new BalanceChargedHandler(); // Mockito.spy(new BalanceChargedHandler());
        handlers.add(handler);
        DefaultEventDispatcher eventDispatcher = new DefaultEventDispatcher(handlers);
        BalanceChargedEvent balanceChargedEvent = new BalanceChargedEvent(BigDecimal.valueOf(50).doubleValue());

        eventDispatcher.dispatchEvent(balanceChargedEvent);

        // Mockito.verify(handler, VerificationModeFactory.times(1)).handle(balanceChargedEvent);
    }
}
