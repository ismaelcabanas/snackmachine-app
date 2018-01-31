package cabanas.garcia.ismael.dddinpractice.snackmachine.domain.model;

import cabanas.garcia.ismael.dddinpractice.shared.domain.model.Money;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

public class SnackMachineShould {

    private static final Money DOLLAR = new Money(0, 0, 0, 1, 0, 0);
    private static final Money QUARTER_DOLLAR = new Money(0, 0, 1, 0, 0, 0);
    private static final Money TEN_DOLLAR = new Money(0, 0, 0, 10, 0, 0);

    private static final short FIRST_POSITION = 1;
    public static final String APPLE_NAME = "Apple";

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
        snackMachine.loadSnacks(FIRST_POSITION, new SnackPile(new Snack(APPLE_NAME), 10, BigDecimal.ONE));
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
        snackMachine.loadSnacks(FIRST_POSITION, new SnackPile(new Snack(APPLE_NAME), 10, BigDecimal.ONE));
        snackMachine.insertMoney(DOLLAR);

        snackMachine.buySnack(FIRST_POSITION);

        assertThat(snackMachine.snacksOfSlot(1)).isEqualTo(9);
    }

    @Test public void
    cannot_buy_when_not_exist_snacks() {
        SnackMachine snackMachine = new SnackMachine();
        snackMachine.insertMoney(DOLLAR);
        exception.expect(SnackNotFoundException.class);

        snackMachine.buySnack(FIRST_POSITION);
    }

    @Test public void
    cannot_buy_if_not_enough_money_inserted() {
        SnackMachine snackMachine = new SnackMachine();
        snackMachine.loadSnacks(FIRST_POSITION, new SnackPile(new Snack(APPLE_NAME), 10, new BigDecimal(2)));
        snackMachine.insertMoney(DOLLAR);
        exception.expect(NotEnoughMoneyInsertedException.class);

        snackMachine.buySnack(FIRST_POSITION);
    }

    @Test public void
    snack_machine_returns_money_with_highest_denomination_first() {
        SnackMachine snackMachine = new SnackMachine();
        snackMachine.loadMoney(DOLLAR);
        snackMachine.insertMoney(QUARTER_DOLLAR);
        snackMachine.insertMoney(QUARTER_DOLLAR);
        snackMachine.insertMoney(QUARTER_DOLLAR);
        snackMachine.insertMoney(QUARTER_DOLLAR);

        snackMachine.returnMoney();

        assertThat(snackMachine.moneyInside().quarterCount()).isEqualTo(4);
        assertThat(snackMachine.moneyInside().dollarCount()).isEqualTo(0);
    }

    @Test public void
    after_purchase_change_is_returned() {
        SnackMachine snackMachine = new SnackMachine();
        snackMachine.loadSnacks(FIRST_POSITION, new SnackPile(new Snack(APPLE_NAME), 10, new BigDecimal("0.5")));
        snackMachine.loadMoney(TEN_DOLLAR);
        snackMachine.loadMoney(QUARTER_DOLLAR);
        snackMachine.loadMoney(QUARTER_DOLLAR);
        snackMachine.insertMoney(DOLLAR);

        snackMachine.buySnack(FIRST_POSITION);

        assertThat(snackMachine.amountInTransaction()).isEqualTo(0);
        assertThat(snackMachine.moneyInside().amount()).isEqualTo(11.0);
    }

    @Test public void
    cannot_buy_snack_if_not_enough_change() {
        SnackMachine snackMachine = new SnackMachine();
        snackMachine.loadSnacks(FIRST_POSITION, new SnackPile(new Snack(APPLE_NAME), 10, new BigDecimal("0.5")));
        snackMachine.loadMoney(TEN_DOLLAR);
        snackMachine.insertMoney(DOLLAR);
        exception.expect(NoChangeException.class);

        snackMachine.buySnack(FIRST_POSITION);
    }
}
