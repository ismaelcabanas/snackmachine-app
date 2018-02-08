package cabanas.garcia.ismael.dddinpractice.management.domain.model;

import cabanas.garcia.ismael.dddinpractice.atm.domain.model.Atm;
import cabanas.garcia.ismael.dddinpractice.common.AgreggateRoot;
import cabanas.garcia.ismael.dddinpractice.shared.domain.model.Money;
import cabanas.garcia.ismael.dddinpractice.snackmachine.domain.model.SnackMachine;

public class HeadOffice extends AgreggateRoot<HeadOfficeId> {

    private double balance;
    private Money cash;

    public HeadOffice() {
        super(new HeadOfficeId());
        this.balance = 0;
        this.cash = Money.none();
    }

    private HeadOffice(Builder builder) {
        super(builder.headOfficeId);
        this.balance = builder.balance;
        this.cash = new Money(builder.oneCentCount,
                builder.tenCentCount,
                builder.quarterCentCount,
                builder.oneDollarCount,
                builder.fiveDollarCount,
                builder.twentyDollarCount);
    }

    public void changeBalance(double amount) {
        this.balance += amount;
    }

    public double balance() {
        return balance;
    }

    public static Builder builder(HeadOfficeId headOfficeId) {
        return new Builder(headOfficeId);
    }

    public Money cash() {
        return cash;
    }

    public void unloadCash(SnackMachine snackMachine) {
        Money money = snackMachine.unloadMoney();
        cash = cash.add(money);
    }

    public void loadCash(Atm am) {
        am.loadMoney(cash);
        cash = Money.none();
    }

    public static class Builder {

        private final HeadOfficeId headOfficeId;
        private double balance;
        private int oneCentCount;
        private int tenCentCount;
        private int twentyDollarCount;
        private int quarterCentCount;
        private int oneDollarCount;
        private int fiveDollarCount;

        public Builder(HeadOfficeId headOfficeId) {
            this.headOfficeId = headOfficeId;
        }

        public Builder setBalance(double balance) {
            this.balance = balance;
            return this;
        }

        public HeadOffice build() {
            return new HeadOffice(this);
        }

        public Builder setOneCentCount(int oneCentCount) {
            this.oneCentCount = oneCentCount;
            return this;
        }

        public Builder setTenCentCount(int tenCentCount) {
            this.tenCentCount = tenCentCount;
            return this;
        }

        public Builder setQuarterCentCount(int quarterCentCount) {
            this.quarterCentCount = quarterCentCount;
            return this;
        }

        public Builder setOneDollarCount(int oneDollarCount) {
            this.oneDollarCount = oneDollarCount;
            return this;
        }

        public Builder setFiveDollarCount(int fiveDollarCount) {
            this.fiveDollarCount = fiveDollarCount;
            return this;
        }

        public Builder setTwentyDollarCount(int twentyDollarCount) {
            this.twentyDollarCount = twentyDollarCount;
            return this;
        }
    }
}
