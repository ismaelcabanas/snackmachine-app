package cabanas.garcia.ismael.dddinpractice.shared.domain.service;

import cabanas.garcia.ismael.dddinpractice.atm.domain.model.BalanceChargedEvent;
import cabanas.garcia.ismael.dddinpractice.management.domain.model.BalanceChargedHandler;
import cabanas.garcia.ismael.dddinpractice.management.domain.repository.HeadOfficeRepository;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class DefaultEventDispatcherShould {

    //Creating new rule with recommended Strictness setting
    @Rule
    public MockitoRule rule = MockitoJUnit.rule();

    @Mock
    private HeadOfficeRepository headOfficeRepository;

    @Test public void
    dispatch_an_event() {
        List<Handler> handlers = new ArrayList<>();
        BalanceChargedHandler handler = new BalanceChargedHandler(headOfficeRepository); // Mockito.spy(new BalanceChargedHandler());
        handlers.add(handler);
        DefaultEventDispatcher eventDispatcher = new DefaultEventDispatcher(handlers);
        BalanceChargedEvent balanceChargedEvent = new BalanceChargedEvent(BigDecimal.valueOf(50).doubleValue());

        eventDispatcher.dispatchEvent(balanceChargedEvent);

        // Mockito.verify(handler, VerificationModeFactory.times(1)).handle(balanceChargedEvent);
    }
}
