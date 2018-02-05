package cabanas.garcia.ismael.dddinpractice.shared.domain.model;

import cabanas.garcia.ismael.dddinpractice.snackmachine.domain.model.NoChangeException;
import cabanas.garcia.ismael.dddinpractice.common.ValueObject;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

import static java.math.BigDecimal.ONE;

public class Money extends ValueObject<Money> {
    private static final String THE_PARAMETER_SHOULD_BE_A_POSITIVE_NUMBER = "The parameter should be a positive number";

    private static final BigDecimal ONE_CENT = new BigDecimal("0.01");
    private static final BigDecimal TEN_CENTS = new BigDecimal("0.1");
    private static final BigDecimal QUARTER_CENTS = new BigDecimal("0.25");
    private static final BigDecimal ONE_DOLLAR = ONE;
    private static final BigDecimal FIVE_DOLLARS = new BigDecimal("5");
    private static final BigDecimal TWENTY_DOLLARS = new BigDecimal("20");

    public static final Money CENT = new Money(1, 0, 0, 0, 0, 0);
    public static final Money TEN_CENT = new Money(0, 1, 0, 0, 0, 0);
    public static final Money QUARTER_CENT = new Money(0, 0, 1, 0, 0, 0);
    public static final Money DOLLAR = new Money(0, 0, 0, 1, 0, 0);
    public static final Money FIVE_DOLLAR = new Money(0, 0, 0, 0, 1, 0);
    public static final Money TWENTY_DOLLAR = new Money(0, 0, 0, 0, 0, 1);

    private static final BigDecimal TWENTY = BigDecimal.valueOf(20);
    private static final BigDecimal FIVE = BigDecimal.valueOf(5);
    private static final BigDecimal QUARTER = BigDecimal.valueOf(0.25);
    private static final BigDecimal ZERO_POINT_ONE = BigDecimal.valueOf(0.1);
    private static final BigDecimal ZERO_POINT_ZERO_ONE = BigDecimal.valueOf(0.01);

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

    public Money(Money money) {
        this(money.getOneCentCount(), money.getTenCentCount(), money.getQuarterCentCount(), money.oneDollarCount,
                money.fiveDollarCount, money.twentyDollarCount);
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
        return new Money(oneCentCount + money.getOneCentCount(),
                tenCentCount + money.getTenCentCount(),
                quarterCentCount + money.getQuarterCentCount(),
                oneDollarCount + money.getOneDollarCount(),
                fiveDollarCount + money.fiveDollarCount,
                twentyDollarCount + money.twentyDollarCount);
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
        return new Money(oneCentCount - money.getOneCentCount(),
                tenCentCount - money.getTenCentCount(),
                quarterCentCount - money.getQuarterCentCount(),
                oneDollarCount - money.getOneDollarCount(),
                fiveDollarCount - money.fiveDollarCount,
                twentyDollarCount - money.twentyDollarCount);
    }

    public Money allocate(double amount) {
        BigDecimal theAmount = new BigDecimal(Double.valueOf(amount).toString()).setScale(2, RoundingMode.FLOOR);
        int twentyDollarCountAllocated = theAmount.divide(TWENTY).min(new BigDecimal(this.twentyDollarCount)).intValue();
        theAmount = theAmount.subtract(new BigDecimal(twentyDollarCountAllocated).multiply(TWENTY));
        int fiveDollarCountAllocated = theAmount.divide(FIVE).min(new BigDecimal(this.fiveDollarCount)).intValue();
        theAmount = theAmount.subtract(new BigDecimal(fiveDollarCountAllocated).multiply(FIVE));
        int oneDollarCountAllocated = theAmount.divide(ONE).min(new BigDecimal(this.oneDollarCount)).intValue();
        theAmount = theAmount.subtract(new BigDecimal(oneDollarCountAllocated).multiply(ONE));
        int quarterCentCountAllocated = theAmount.divide(QUARTER).min(new BigDecimal(this.quarterCentCount)).intValue();
        theAmount = theAmount.subtract(new BigDecimal(quarterCentCountAllocated).multiply(QUARTER));
        int tenCentCountAllocated = theAmount.divide(ZERO_POINT_ONE).min(new BigDecimal(this.tenCentCount)).intValue();
        theAmount = theAmount.subtract(new BigDecimal(tenCentCountAllocated).multiply(ZERO_POINT_ONE));
        int oneCentCountAllocated = theAmount.divide(ZERO_POINT_ZERO_ONE).min(new BigDecimal(this.oneCentCount)).intValue();
        theAmount = theAmount.subtract(new BigDecimal(oneCentCountAllocated).multiply(ZERO_POINT_ZERO_ONE));

        if (theAmount.doubleValue() != 0) {
            throw new NoChangeException();
        }

        Money change = new Money(oneCentCountAllocated, tenCentCountAllocated, quarterCentCountAllocated,
                oneDollarCountAllocated, fiveDollarCountAllocated, twentyDollarCountAllocated);

        return change;
    }

    @Override
    public String toString() {
        double amount = amount();
        if (amount < 1) {
            return String.format("¢%d", Double.valueOf(amount * 100).intValue());
        }

        return String.format("$%.2f", amount);
    }
}
