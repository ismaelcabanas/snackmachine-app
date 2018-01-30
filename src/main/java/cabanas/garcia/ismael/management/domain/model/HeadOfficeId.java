package cabanas.garcia.ismael.management.domain.model;

import cabanas.garcia.ismael.snackmachine.domain.model.ValueObject;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.util.UUID;

public class HeadOfficeId extends ValueObject<HeadOfficeId> {
    private final String value;

    public HeadOfficeId() {
        this.value = UUID.randomUUID().toString();
    }

    public HeadOfficeId(String value) {
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
    protected boolean equalsCore(HeadOfficeId other) {
        return new EqualsBuilder()
                .append(value, other.value)
                .isEquals();
    }
}
