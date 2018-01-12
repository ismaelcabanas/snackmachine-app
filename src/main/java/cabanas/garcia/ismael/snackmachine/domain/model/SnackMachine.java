package cabanas.garcia.ismael.snackmachine.domain.model;

import java.util.Arrays;
import java.util.List;

public class SnackMachine extends Entity {

    private static final List<Money> COINS_AND_NOTES = Arrays.asList(
        Money.CENT, Money.TEN_CENT, Money.QUARTER_CENT,
        Money.DOLLAR, Money.FIVE_DOLLAR, Money.TWENTY_DOLLAR);

    private Money moneyInside;
    private Money moneyInTransaction;

    public SnackMachine() {
        this.moneyInside = Money.none();
        this.moneyInTransaction = Money.none();
    }

    public void insertMoney(Money money) {
        if (!COINS_AND_NOTES.contains(money)) {
            throw new BadMoneyException();
        }
        this.moneyInTransaction.add(money);
    }

    public void returnMoney() {
        this.moneyInTransaction = Money.none();
    }

    public void buySnack() {
        this.moneyInside.add(this.moneyInTransaction);
        this.moneyInTransaction = Money.none();
    }

    public double amountInTransaction() {
        return this.moneyInTransaction.amount();
    }

    public double amountInside() {
        return this.moneyInside.amount();
    }
}
