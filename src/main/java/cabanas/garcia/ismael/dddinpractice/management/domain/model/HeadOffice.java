package cabanas.garcia.ismael.dddinpractice.management.domain.model;

import cabanas.garcia.ismael.dddinpractice.common.AgreggateRoot;

public class HeadOffice extends AgreggateRoot<HeadOfficeId> {

    private double balance;

    public HeadOffice() {
        super(new HeadOfficeId());
        this.balance = 0;
    }

    public void changeBalance(double amount) {
        this.balance += amount;
    }

    public double balance() {
        return balance;
    }
}
