package cabanas.garcia.ismael.dddinpractice.atm.domain.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class AtmDto {
    private final double amountInside;

    public AtmDto(double amountInside) {
        this.amountInside = amountInside;
    }

    public double getAmountInside() {
        return amountInside;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        AtmDto atmDto = (AtmDto) o;

        return new EqualsBuilder()
                .append(amountInside, atmDto.amountInside)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(amountInside)
                .toHashCode();
    }
}
