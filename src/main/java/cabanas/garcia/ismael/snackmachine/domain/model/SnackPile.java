package cabanas.garcia.ismael.snackmachine.domain.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.math.BigDecimal;

public class SnackPile extends ValueObject<SnackPile> {

    public static final SnackPile EMPTY = new SnackPile(null, 0, BigDecimal.ZERO);

    private final Snack snack;
    private int quantity;
    private final BigDecimal price;

    public SnackPile(Snack snack, int quantity, BigDecimal price) {
        this.snack = snack;
        this.quantity = quantity;
        this.price = price;
    }

    public SnackPile(Builder builder) {
        this.snack = builder.snack;
        this.quantity = builder.quantity;
        this.price = builder.price;
    }

    @Override
    protected int hashCodeCore() {
        return new HashCodeBuilder(17, 37)
                .append(snack)
                .append(quantity)
                .append(price)
                .toHashCode();
    }

    @Override
    protected boolean equalsCore(SnackPile other) {
        return new EqualsBuilder()
                .append(quantity, other.quantity)
                .append(snack, other.snack)
                .append(price, other.price)
                .isEquals();
    }

    public SnackPile drop() {
        return new SnackPile(snack, quantity - 1, price);
    }

    public int quantity() {
        return quantity;
    }

    public double price() {
        return price.doubleValue();
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private Snack snack;
        private int quantity;
        private BigDecimal price;

        public Builder setSnack(Snack snack) {
            this.snack = snack;
            return this;
        }

        public Builder setQuantity(int quantity) {
            this.quantity = quantity;
            return this;
        }

        public Builder setPrice(BigDecimal price) {
            this.price = price;
            return this;
        }

        public SnackPile build() {
            return new SnackPile(this);
        }
    }
}
