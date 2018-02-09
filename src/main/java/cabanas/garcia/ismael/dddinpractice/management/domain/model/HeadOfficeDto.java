package cabanas.garcia.ismael.dddinpractice.management.domain.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class HeadOfficeDto {
    private final double cashAmount;
    private final double balance;

    public HeadOfficeDto(double cashAmount, double balance) {
        this.cashAmount = cashAmount;
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "HeadOfficeDto{"
                + "cashAmount=" + cashAmount
                + ", balance=" + balance
                + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        HeadOfficeDto that = (HeadOfficeDto) o;

        return new EqualsBuilder()
                .append(cashAmount, that.cashAmount)
                .append(balance, that.balance)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(cashAmount)
                .append(balance)
                .toHashCode();
    }
}
