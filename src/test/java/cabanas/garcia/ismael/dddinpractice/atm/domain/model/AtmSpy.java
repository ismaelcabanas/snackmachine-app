package cabanas.garcia.ismael.dddinpractice.atm.domain.model;

import cabanas.garcia.ismael.dddinpractice.common.DomainEvent;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

public class AtmSpy extends Atm {

    public void shouldRaiseBalanceChargedEvent(double amount) {
        Optional<DomainEvent> domainEvent = this.getDomainEvents().stream().findFirst();

        BalanceChargedEvent balanceChargedEvent = (BalanceChargedEvent) domainEvent.get();

        assertThat(balanceChargedEvent).isNotNull();
        assertThat(balanceChargedEvent.getAmountCharged()).isEqualTo(amount);
    }
}
