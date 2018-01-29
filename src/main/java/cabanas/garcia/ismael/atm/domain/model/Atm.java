package cabanas.garcia.ismael.atm.domain.model;

import cabanas.garcia.ismael.snackmachine.domain.model.AgreggateRoot;
import cabanas.garcia.ismael.snackmachine.domain.model.Money;

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

}
