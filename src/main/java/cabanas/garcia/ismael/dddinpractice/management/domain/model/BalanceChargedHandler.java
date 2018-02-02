package cabanas.garcia.ismael.dddinpractice.management.domain.model;

import cabanas.garcia.ismael.dddinpractice.atm.domain.model.BalanceChargedEvent;
import cabanas.garcia.ismael.dddinpractice.shared.domain.service.Handler;

public class BalanceChargedHandler implements Handler<BalanceChargedEvent> {

    @Override
    public void handle(BalanceChargedEvent balanceChargedEvent) {
        System.out.print(balanceChargedEvent.getAmountCharged());
    }
}
