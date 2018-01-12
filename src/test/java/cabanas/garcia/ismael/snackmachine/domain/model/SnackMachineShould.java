package cabanas.garcia.ismael.snackmachine.domain.model;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.assertj.core.api.Assertions.assertThat;

public class SnackMachineShould {

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test public void
    empty_the_money_in_transaction_when_return_money() {
        SnackMachine snackMachine = new SnackMachine();
        snackMachine.insertMoney(new Money(1, 0, 0, 0, 0, 0));

        snackMachine.returnMoney();

        assertThat(snackMachine.amountInTransaction()).isEqualTo(0.00);
    }

    @Test public void
    accumulate_money_in_transaction_when_insert_money() {
        SnackMachine snackMachine = new SnackMachine();

        snackMachine.insertMoney(new Money(1, 0, 0, 0, 0, 0));

        assertThat(snackMachine.amountInTransaction()).isEqualTo(0.01);
    }

    @Test public void
    cannot_insert_more_than_one_coin_or_note_at_a_time() {
        SnackMachine snackMachine = new SnackMachine();
        exception.expect(BadMoneyException.class);
        Money twoCents = new Money(2, 0, 0, 0, 0, 0);

        snackMachine.insertMoney(twoCents);
    }

    @Test public void
    updated_money_inside_after_buy_snack() {
        SnackMachine snackMachine = new SnackMachine();
        snackMachine.insertMoney(new Money(0, 0, 0, 1, 0, 0));
        snackMachine.buySnack();
        snackMachine.insertMoney(new Money(0, 0, 0, 1, 0, 0));

        snackMachine.buySnack();

        assertThat(snackMachine.amountInTransaction()).isEqualTo(0.00);
        assertThat(snackMachine.amountInside()).isEqualTo(2.00);

    }
}
