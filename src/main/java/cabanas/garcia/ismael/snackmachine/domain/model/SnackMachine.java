package cabanas.garcia.ismael.snackmachine.domain.model;

public class SnackMachine extends Entity {

    private Money moneyInside;
    private Money moneyInTransaction;

    public SnackMachine() {
        this.moneyInTransaction = Money.none();
    }

    public void insertMoney(Money money) {
        this.moneyInTransaction.add(money);
    }

    public void returnMoney() {
        this.moneyInTransaction = Money.none();
    }

    public void buySnack() {
        this.moneyInside.add(this.moneyInTransaction);

        //this.moneyInTransaction = 0;
    }

    public double amountInTransaction() {
        return this.moneyInTransaction.amount();
    }
}
