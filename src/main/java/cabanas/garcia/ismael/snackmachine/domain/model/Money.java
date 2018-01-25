package cabanas.garcia.ismael.snackmachine.domain.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.math.BigDecimal;
import java.math.MathContext;

public class Money extends ValueObject<Money> {
    private static final String THE_PARAMETER_SHOULD_BE_A_POSITIVE_NUMBER = "The parameter should be a positive number";

    private static final BigDecimal ONE_CENT = new BigDecimal("0.01");
    private static final BigDecimal TEN_CENTS = new BigDecimal("0.1");
    private static final BigDecimal QUARTER_CENTS = new BigDecimal("0.25");
    private static final BigDecimal ONE_DOLLAR = BigDecimal.ONE;
    private static final BigDecimal FIVE_DOLLARS = new BigDecimal("5");
    private static final BigDecimal TWENTY_DOLLARS = new BigDecimal("20");

    public static final Money CENT = new Money(1, 0, 0, 0, 0, 0);
    public static final Money TEN_CENT = new Money(0, 1, 0, 0, 0, 0);
    public static final Money QUARTER_CENT = new Money(0, 0, 1, 0, 0, 0);
    public static final Money DOLLAR = new Money(0, 0, 0, 1, 0, 0);
    public static final Money FIVE_DOLLAR = new Money(0, 0, 0, 0, 1, 0);
    public static final Money TWENTY_DOLLAR = new Money(0, 0, 0, 0, 0, 1);

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

    public double amount() {
        return new BigDecimal(oneCentCount).multiply(ONE_CENT)
                .add(new BigDecimal(tenCentCount).multiply(TEN_CENTS))
                .add(new BigDecimal(quarterCentCount).multiply(QUARTER_CENTS))
                .add(new BigDecimal(oneDollarCount).multiply(ONE_DOLLAR))
                .add(new BigDecimal(fiveDollarCount).multiply(FIVE_DOLLARS))
                .add(new BigDecimal(twentyDollarCount).multiply(TWENTY_DOLLARS))
                .round(MathContext.DECIMAL32)
                .doubleValue();
    }

    public static Money none() {
        return new Money(0, 0, 0, 0, 0, 0);
    }

    public int quarterCount() {
        return quarterCentCount;
    }

    public int dollarCount() {
        return oneDollarCount;
    }

    public Money substract(Money money) {
        this.oneCentCount -= money.getOneCentCount();
        this.tenCentCount -= money.getTenCentCount();
        this.quarterCentCount -= money.getQuarterCentCount();
        this.oneDollarCount -= money.getOneDollarCount();
        this.fiveDollarCount -= money.getFiveDollarCount();
        this.twentyDollarCount -= money.getTwentyDollarCount();
        return new Money(oneCentCount, tenCentCount, quarterCentCount, oneDollarCount, fiveDollarCount, twentyDollarCount);
    }

    public Money allocate(double amount) {
        int twentyDollarCountAllocated = Double.valueOf(Math.min(amount / 20, this.twentyDollarCount)).intValue();
        amount = amount - twentyDollarCountAllocated * 20;
        int fiveDollarCountAllocated = Double.valueOf(Math.min(amount / 5, this.fiveDollarCount)).intValue();
        amount = amount - fiveDollarCountAllocated * 5;
        int oneDollarCountAllocated = Double.valueOf(Math.min(amount / 1, this.oneDollarCount)).intValue();
        amount = amount - oneDollarCountAllocated * 1;
        int quarterCentCountAllocated = Double.valueOf(Math.min(amount / 0.25, this.quarterCentCount)).intValue();
        amount = amount - quarterCentCountAllocated * 0.25;
        int tenCentCountAllocated = Double.valueOf(Math.min(amount / 0.1, this.tenCentCount)).intValue();
        amount = amount - tenCentCountAllocated * 0.1;
        int oneCentCountAllocated = Double.valueOf(Math.min(amount / 0.01, this.oneCentCount)).intValue();
        amount = amount - oneCentCountAllocated * 0.01;

        Money change = new Money(oneCentCountAllocated, tenCentCountAllocated, quarterCentCountAllocated,
                oneDollarCountAllocated, fiveDollarCountAllocated, twentyDollarCountAllocated);

        if(amount != 0) {
            throw new NoChangeException();
        }

        return change;
    }

    @Override
    public String toString() {
        double amount = amount();
        if(amount < 1) {
            return String.format("¢%d", Double.valueOf(amount * 100).intValue());
        }

        return String.format("$%.2f", amount);
    }
}
