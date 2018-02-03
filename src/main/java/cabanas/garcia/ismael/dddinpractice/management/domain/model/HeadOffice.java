package cabanas.garcia.ismael.dddinpractice.management.domain.model;

import cabanas.garcia.ismael.dddinpractice.common.AgreggateRoot;

public class HeadOffice extends AgreggateRoot<HeadOfficeId> {

    private double balance;

    public HeadOffice() {
        super(new HeadOfficeId());
        this.balance = 0;
    }

    private HeadOffice(Builder builder) {
        super(builder.headOfficeId);
        this.balance = builder.balance;
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

    public static class Builder {

        private final HeadOfficeId headOfficeId;
        private double balance;

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
    }
}
