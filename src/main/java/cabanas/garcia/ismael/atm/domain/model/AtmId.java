package cabanas.garcia.ismael.atm.domain.model;

import cabanas.garcia.ismael.common.ValueObject;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.util.UUID;

public class AtmId extends ValueObject<AtmId> {

    private final String value;

    public AtmId() {
        this.value = UUID.randomUUID().toString();
    }

    public AtmId(String value) {
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
    protected boolean equalsCore(AtmId other) {
        return new EqualsBuilder()
                .append(value, other.value)
                .isEquals();
    }
}
