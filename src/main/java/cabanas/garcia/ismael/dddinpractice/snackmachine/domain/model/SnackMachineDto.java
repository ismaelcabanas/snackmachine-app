package cabanas.garcia.ismael.dddinpractice.snackmachine.domain.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class SnackMachineDto {
    private final double amountInside;
    private final double amountInTransaction;

    public SnackMachineDto(double amountInside, double amountInTransaction) {
        this.amountInside = amountInside;
        this.amountInTransaction = amountInTransaction;
    }

    @Override
    public String toString() {
        return "SnackMachineDto{" +
                "amountInside=" + amountInside +
                ", amountInTransaction=" + amountInTransaction +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        SnackMachineDto that = (SnackMachineDto) o;

        return new EqualsBuilder()
                .append(amountInside, that.amountInside)
                .append(amountInTransaction, that.amountInTransaction)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(amountInside)
                .append(amountInTransaction)
                .toHashCode();
    }
}
