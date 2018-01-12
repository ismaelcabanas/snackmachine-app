package cabanas.garcia.ismael.snackmachine.domain.model;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class SnackMachineShould {

    @Test public void
    empty_the_money_in_transaction_when_return_money() {
        SnackMachine snackMachine = new SnackMachine();
        snackMachine.insertMoney(new Money(1, 0, 0, 0, 0, 0));

        snackMachine.returnMoney();

        assertThat(snackMachine.amountInTransaction()).isEqualTo(0.00);
    }

}
