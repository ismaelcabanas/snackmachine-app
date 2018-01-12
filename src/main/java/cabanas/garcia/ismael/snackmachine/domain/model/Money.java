package cabanas.garcia.ismael.snackmachine.domain.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class Money extends ValueObject<Money> {
    private static final String THE_PARAMETER_SHOULD_BE_A_POSITIVE_NUMBER = "The parameter should be a positive number";

    private int oneCentCount;
    private int tenCentCount;
    private int quarterCentCount;
    private int oneDollarCount;
    private int fiveDollarCount;
    private int twentyDollarCount;

    public Money(int oneCentCount, int tenCentCount, int quarterCentCount, int oneDollarCount, int fiveDollarCount, int twentyDollarCount) {
        if (oneCentCount < 0) {
            throw new IllegalArgumentException(THE_PARAMETER_SHOULD_BE_A_POSITIVE_NUMBER);
        }
        if (tenCentCount < 0) {
            throw new IllegalArgumentException(THE_PARAMETER_SHOULD_BE_A_POSITIVE_NUMBER);
        }
        if (quarterCentCount < 0) {
            throw new IllegalArgumentException(THE_PARAMETER_SHOULD_BE_A_POSITIVE_NUMBER);
        }
        if (oneDollarCount < 0) {
            throw new IllegalArgumentException(THE_PARAMETER_SHOULD_BE_A_POSITIVE_NUMBER);
        }
        if (fiveDollarCount < 0) {
            throw new IllegalArgumentException(THE_PARAMETER_SHOULD_BE_A_POSITIVE_NUMBER);
        }
        if (twentyDollarCount < 0) {
            throw new IllegalArgumentException(THE_PARAMETER_SHOULD_BE_A_POSITIVE_NUMBER);
        }
        this.oneCentCount = oneCentCount;
        this.tenCentCount = tenCentCount;
        this.quarterCentCount = quarterCentCount;
        this.oneDollarCount = oneDollarCount;
        this.fiveDollarCount = fiveDollarCount;
        this.twentyDollarCount = twentyDollarCount;
    }

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

    public Money add(Money money) {
        this.oneCentCount += money.getOneCentCount();
        this.tenCentCount += money.getTenCentCount();
        this.quarterCentCount += money.getQuarterCentCount();
        this.oneDollarCount += money.getOneDollarCount();
        this.fiveDollarCount += money.getFiveDollarCount();
        this.twentyDollarCount += money.getTwentyDollarCount();
        return new Money(oneCentCount, tenCentCount, quarterCentCount, oneDollarCount, fiveDollarCount, twentyDollarCount);
    }

    @Override
    protected int hashCodeCore() {
        return new HashCodeBuilder(17, 37)
                .appendSuper(super.hashCode())
                .append(oneCentCount)
                .append(tenCentCount)
                .append(quarterCentCount)
                .append(oneDollarCount)
                .append(fiveDollarCount)
                .append(twentyDollarCount)
                .toHashCode();
    }

    @Override
    protected boolean equalsCore(Money other) {
        if (this == other) {
            return true;
        }

        if (other == null || getClass() != other.getClass()) {
            return false;
        }

        return new EqualsBuilder()
                .append(oneCentCount, other.oneCentCount)
                .append(tenCentCount, other.tenCentCount)
                .append(quarterCentCount, other.quarterCentCount)
                .append(oneDollarCount, other.oneDollarCount)
                .append(fiveDollarCount, other.fiveDollarCount)
                .append(twentyDollarCount, other.twentyDollarCount)
                .isEquals();
    }

}
