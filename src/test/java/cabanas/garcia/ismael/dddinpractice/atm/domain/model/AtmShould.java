package cabanas.garcia.ismael.dddinpractice.atm.domain.model;

import cabanas.garcia.ismael.dddinpractice.shared.domain.model.Money;
import org.junit.Test;

import static cabanas.garcia.ismael.dddinpractice.shared.domain.model.Money.CENT;
import static cabanas.garcia.ismael.dddinpractice.shared.domain.model.Money.DOLLAR;
import static cabanas.garcia.ismael.dddinpractice.shared.domain.model.Money.TEN_CENT;
import static org.assertj.core.api.Assertions.assertThat;

public class AtmShould {

    @Test public void
    withdraw_money_exchange_money_with_comision() {
        Atm atm = new Atm();
        atm.loadMoney(DOLLAR);

        atm.withdraw(1);

        assertThat(atm.moneyInside().amount()).isEqualTo(0);
        assertThat(atm.moneyCharged()).isEqualTo(1.01);
    }

    @Test public void
    comission_applied_is_at_least_one_cent() {
        Atm atm = new Atm();
        atm.loadMoney(CENT);

        atm.withdraw(0.01);

        assertThat(atm.moneyCharged()).isEqualTo(0.02);
    }

    @Test public void
    comission_is_rounded_up_to_the_next_cent() {
        Atm atm = new Atm();
        Money oneDollarAndTenCents = DOLLAR.add(TEN_CENT);
        atm.loadMoney(oneDollarAndTenCents);

        atm.withdraw(1.1);

        assertThat(atm.moneyCharged()).isEqualTo(1.12);
    }

    @Test public void
    raise_balance_changed_event_when_withdraw() {
        AtmSpy atm = new AtmSpy();
        atm.loadMoney(DOLLAR);

        atm.withdraw(1.0);

        atm.shouldRaiseBalanceChargedEvent(1.01);
    }
}
