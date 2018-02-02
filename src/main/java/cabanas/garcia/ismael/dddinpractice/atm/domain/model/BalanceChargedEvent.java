package cabanas.garcia.ismael.dddinpractice.atm.domain.model;

import cabanas.garcia.ismael.dddinpractice.common.DomainEvent;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class BalanceChargedEvent implements DomainEvent {

    private final double amountCharged;

    public BalanceChargedEvent(double amountCharged) {
        this.amountCharged = amountCharged;
    }

    public double getAmountCharged() {
        return amountCharged;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        BalanceChargedEvent that = (BalanceChargedEvent) o;

        return new EqualsBuilder()
                .append(amountCharged, that.amountCharged)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(amountCharged)
                .toHashCode();
    }
}
