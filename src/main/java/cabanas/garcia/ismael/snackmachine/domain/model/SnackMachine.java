package cabanas.garcia.ismael.snackmachine.domain.model;

public class SnackMachine {

    private Money moneyInside;
    private Money moneyInTransaction;

    public void insertMoney(Money money) {
        this.moneyInTransaction.add(money);
    }

    public void returnMoney() {
        //this.moneyInTransaction = 0;
    }

    public void buySnack() {
        this.moneyInside.add(this.moneyInTransaction);

        //this.moneyInTransaction = 0;
    }
}
