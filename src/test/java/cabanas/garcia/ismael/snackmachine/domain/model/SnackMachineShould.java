package cabanas.garcia.ismael.snackmachine.domain.model;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

public class SnackMachineShould {

    private static final Money DOLLAR = new Money(0, 0, 0, 1, 0, 0);

    private static final short FIRST_POSITION = 1;

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
    buySnack_update_money_inside() {
        SnackMachine snackMachine = new SnackMachine();
        snackMachine.addSnacks(Integer.valueOf(1).shortValue(), new Snack("Apple"), 10, new BigDecimal(1));
        snackMachine.insertMoney(DOLLAR);
        snackMachine.buySnack(FIRST_POSITION);
        snackMachine.insertMoney(DOLLAR);

        snackMachine.buySnack(FIRST_POSITION);

        assertThat(snackMachine.amountInTransaction()).isEqualTo(0.00);
        assertThat(snackMachine.amountInside()).isEqualTo(2.00);
    }

    @Test public void
    buySnack_trades_inserted_money_for_a_snack() {
        SnackMachine snackMachine = new SnackMachine();
        snackMachine.addSnacks(Integer.valueOf(1).shortValue(), new Snack("Apple"), 10, new BigDecimal(1));
        snackMachine.insertMoney(DOLLAR);

        snackMachine.buySnack(FIRST_POSITION);

        assertThat(snackMachine.snacksOfSlot(1)).isEqualTo(9);
    }
}
