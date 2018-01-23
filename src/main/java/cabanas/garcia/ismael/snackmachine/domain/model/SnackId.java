package cabanas.garcia.ismael.snackmachine.domain.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.util.UUID;

public class SnackId extends ValueObject<SnackId> {
    private final String value;

    public SnackId() {
        this.value = UUID.randomUUID().toString();
    }

    public SnackId(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    protected int hashCodeCore() {
        return new HashCodeBuilder(17, 37)
                .append(value)
                .toHashCode();
    }

    @Override
    protected boolean equalsCore(SnackId other) {
        return new EqualsBuilder()
                .append(value, other.value)
                .isEquals();
    }
}
