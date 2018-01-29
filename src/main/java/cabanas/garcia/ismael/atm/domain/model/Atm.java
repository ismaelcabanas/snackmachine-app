package cabanas.garcia.ismael.atm.domain.model;

import cabanas.garcia.ismael.snackmachine.domain.model.AgreggateRoot;
import cabanas.garcia.ismael.snackmachine.domain.model.Money;
import cabanas.garcia.ismael.snackmachine.domain.model.SnackMachine;

import java.math.BigDecimal;

public class Atm extends AgreggateRoot<AtmId> {

    public static final double COMISSION_RATE = 0.01;
    private Money moneyInside;
    private double moneyCharged;

    public Atm() {
        super(new AtmId());
        this.moneyInside = Money.none();
        this.moneyCharged = BigDecimal.ZERO.doubleValue();
    }

    public Atm(AtmId atmId) {
        super(atmId);
    }

    private Atm(Builder builder) {
        super(builder.atmId);
        moneyInside = new Money(builder.oneCentCount,
                builder.tenCentCount,
                builder.quarterCentCount,
                builder.oneDollarCount,
                builder.fiveDollarCount,
                builder.twentyDollarCount);
        moneyCharged = builder.amountCharged;
    }

    public void loadMoney(Money money) {
        this.moneyInside = moneyInside.add(money);
    }

    public void withdraw(double amount) {
        Money output = this.moneyInside.allocate(amount);
        this.moneyInside = this.moneyInside.substract(output);

        double amountWithCommission = caluculateAmountWithCommission(amount);
        this.moneyCharged += amountWithCommission;
    }

    public Money moneyInside() {
        return moneyInside;
    }

    public double moneyCharged() {
        return moneyCharged;
    }

    private double caluculateAmountWithCommission(double amount) {
        double comission = amount * COMISSION_RATE;
        double lessThanCent = comission % 0.01;
        if(lessThanCent > 0) {
            comission = comission - lessThanCent + 0.01;
        }

        return amount + comission;
    }

    public static Builder builder(AtmId atmId) {
        return new Builder(atmId);
    }

    public static class Builder {

        private final AtmId atmId;
        private double amountCharged;
        private int oneCentCount;
        private int tenCentCount;
        private int twentyDollarCount;
        private int quarterCentCount;
        private int oneDollarCount;
        private int fiveDollarCount;

        public Builder(AtmId atmId) {
            this.atmId = atmId;
        }

        public Builder setMoneyCharged(double amountCharged) {
            this.amountCharged = amountCharged;
            return this;
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

        public Atm build() {
            return new Atm(this);
        }
    }
}
