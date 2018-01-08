package cabanas.garcia.ismael.snackmachine.domain.model;

public class SnackMachine {

    private int oneCentCount;
    private int tenCentCount;
    private int quarterCentCount;
    private int oneDollarCount;
    private int fiveDollarCount;
    private int twentyDollarCount;

    private int oneCentCountInTransaction;
    private int tenCentCountInTransaction;
    private int quarterCentCountInTransaction;
    private int oneDollarCountInTransaction;
    private int fiveDollarCountInTransaction;
    private int twentyDollarCountInTransaction;

    public void insertMoney(int oneCent, int tenCent, int quarterCent,
                            int oneDollar, int fiveDollar, int twentyDollar) {
        this.oneCentCountInTransaction += oneCent;
        this.tenCentCountInTransaction += tenCent;
        this.quarterCentCountInTransaction += quarterCent;
        this.oneDollarCountInTransaction += oneDollar;
        this.fiveDollarCountInTransaction += fiveDollar;
        this.twentyDollarCountInTransaction += twentyDollar;
    }

    public void returnMoney() {
        this.oneCentCountInTransaction = 0;
        this.tenCentCountInTransaction = 0;
        this.quarterCentCountInTransaction = 0;
        this.oneCentCountInTransaction = 0;
        this.fiveDollarCountInTransaction = 0;
        this.twentyDollarCountInTransaction = 0;
    }

    public void buySnack() {
        this.oneCentCount += oneCentCountInTransaction;
        this.tenCentCount = tenCentCountInTransaction;
        this.quarterCentCount += quarterCentCountInTransaction;
        this.oneDollarCount += oneDollarCountInTransaction;
        this.fiveDollarCount += fiveDollarCountInTransaction;
        this.twentyDollarCount += twentyDollarCountInTransaction;

        this.oneCentCountInTransaction = 0;
        this.tenCentCountInTransaction = 0;
        this.quarterCentCountInTransaction = 0;
        this.oneCentCountInTransaction = 0;
        this.fiveDollarCountInTransaction = 0;
        this.twentyDollarCountInTransaction = 0;
    }
}
