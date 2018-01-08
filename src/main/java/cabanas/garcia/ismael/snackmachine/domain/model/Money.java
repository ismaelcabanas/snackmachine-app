package cabanas.garcia.ismael.snackmachine.domain.model;

public class Money {
    private int oneCentCount;
    private int tenCentCount;
    private int quarterCentCount;
    private int oneDollarCount;
    private int fiveDollarCount;
    private int twentyDollarCount;

    public int getOneCentCount() {
        return oneCentCount;
    }

    public int getTenCentCount() {
        return tenCentCount;
    }

    public int getQuarterCentCount() {
        return quarterCentCount;
    }

    public int getOneDollarCount() {
        return oneDollarCount;
    }

    public int getFiveDollarCount() {
        return fiveDollarCount;
    }

    public int getTwentyDollarCount() {
        return twentyDollarCount;
    }

    public void add(Money money) {
        this.oneCentCount += money.getOneCentCount();
        this.tenCentCount += money.getTenCentCount();
        this.quarterCentCount += money.getQuarterCentCount();
        this.oneDollarCount += money.getOneDollarCount();
        this.fiveDollarCount += money.getFiveDollarCount();
        this.twentyDollarCount += money.getTwentyDollarCount();
    }
}
