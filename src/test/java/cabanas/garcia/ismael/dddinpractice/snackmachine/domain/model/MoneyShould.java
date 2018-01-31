package cabanas.garcia.ismael.dddinpractice.snackmachine.domain.model;

import cabanas.garcia.ismael.dddinpractice.shared.domain.model.Money;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(JUnitParamsRunner.class)
public class MoneyShould {

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test public void
    sum_of_two_moneys_produces_correct_result() {
        Money moneyOne = new Money(1, 2, 3, 4, 5,
                 6);
        Money moneyTwo = new Money(1, 2, 3, 4, 5,
                 6);

        Money actual = moneyOne.add(moneyTwo);

        assertThat(actual.getOneCentCount()).isEqualTo(2);
        assertThat(actual.getTenCentCount()).isEqualTo(4);
        assertThat(actual.getQuarterCentCount()).isEqualTo(6);
        assertThat(actual.getOneDollarCount()).isEqualTo(8);
        assertThat(actual.getFiveDollarCount()).isEqualTo(10);
        assertThat(actual.getTwentyDollarCount()).isEqualTo(12);
    }

    @Test public void
    two_instances_of_money_are_equal_if_contains_same_money_amounts() {
        Money moneyOne = new Money(1, 2, 3, 4, 5,
                6);
        Money moneyTwo = new Money(1, 2, 3, 4, 5,
                6);

        boolean actual = moneyOne.equals(moneyTwo);

        assertThat(actual).isTrue();
    }

    @Test public void
    two_instances_of_money_are_not_equal_if_not_contains_same_money_amounts() {
        Money moneyOne = new Money(1, 2, 3, 4, 5,
                6);
        Money moneyTwo = new Money(2, 2, 3, 4, 5,
                6);

        boolean actual = moneyOne.equals(moneyTwo);

        assertThat(actual).isFalse();
    }

    @Test
    @Parameters({
            "-1, 0, 0, 0, 0, 0",
            "0, -1, 0, 0, 0, 0",
            "0, 0, -1, 0, 0, 0",
            "0, 0, 0, -1, 0, 0",
            "0, 0, 0, 0, -1, 0",
            "0, 0, 0, 0, 0, -1"
        }) public void
    cannot_create_money_with_negative_value(int oneCentCount, int tenCentCount, int quarterCentCount, int oneDollarCount,
                                            int fiveDollarCount, int twentyDollarCount) {
        exception.expect(IllegalArgumentException.class);
        new Money(oneCentCount, tenCentCount, quarterCentCount, oneDollarCount, fiveDollarCount, twentyDollarCount);
    }

    @Test
    @Parameters({
            "0, 0, 0, 0, 0, 0, 0",
            "1, 0, 0, 0, 0, 0, 0.01",
            "1, 2, 0, 0, 0, 0, 0.21",
            "1, 2, 3, 0, 0, 0, 0.96",
            "1, 2, 3, 4, 0, 0, 4.96",
            "1, 2, 3, 4, 5, 0, 29.96",
            "1, 2, 3, 4, 5, 6, 149.96",
            "11, 0, 0, 0, 0, 0, 0.11",
            "110, 0, 0, 0, 100, 0, 501.1"
        }) public void
    calculate_amount_correctly(int oneCentCount, int tenCentCount, int quarterCentCount, int oneDollarCount,
                               int fiveDollarCount, int twentyDollarCount, double expectedAmount) {
        Money money = new Money(oneCentCount, tenCentCount, quarterCentCount, oneDollarCount, fiveDollarCount, twentyDollarCount);

        double amount = money.amount();

        assertThat(amount).isEqualTo(expectedAmount);
    }

    @Test
    @Parameters({
            "1, 0, 0, 0, 0, 0, ¢1",
            "0, 0, 0, 1, 0, 0, $1.00",
            "1, 0, 0, 1, 0, 0, $1.01",
            "0, 0, 2, 1, 0, 0, $1.50"
        }) public void
    toString_returns_correct_string_representation(int oneCentCount, int tenCentCount, int quarterCentCount, int oneDollarCount,
                                                   int fiveDollarCount, int twentyDollarCount, String expectedString) {
        Money money = new Money(
                oneCentCount,
                tenCentCount,
                quarterCentCount,
                oneDollarCount,
                fiveDollarCount,
                twentyDollarCount);

        String actual = money.toString();

        assertThat(actual).isEqualTo(expectedString);
    }
}
