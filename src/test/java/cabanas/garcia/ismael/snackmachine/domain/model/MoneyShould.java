package cabanas.garcia.ismael.snackmachine.domain.model;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class MoneyShould {

    @Test public void
    sum_two_moneys() {
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
    two_instances_of_money_are_equal_if_contains_same_money_account() {
        Money moneyOne = new Money(1, 2, 3, 4, 5,
                6);
        Money moneyTwo = new Money(1, 2, 3, 4, 5,
                6);

        boolean actual = moneyOne.equals(moneyTwo);

        assertThat(actual).isTrue();
    }

    @Test public void
    two_instances_of_money_are_not_equal_if_not_contains_same_money_account() {
        Money moneyOne = new Money(1, 2, 3, 4, 5,
                6);
        Money moneyTwo = new Money(2, 2, 3, 4, 5,
                6);

        boolean actual = moneyOne.equals(moneyTwo);

        assertThat(actual).isFalse();
    }

}
