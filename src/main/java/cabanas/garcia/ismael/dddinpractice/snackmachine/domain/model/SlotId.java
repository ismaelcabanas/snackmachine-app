package cabanas.garcia.ismael.dddinpractice.snackmachine.domain.model;

import cabanas.garcia.ismael.dddinpractice.common.ValueObject;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.util.UUID;

public class SlotId extends ValueObject<SlotId> {

    private final String value;

    public SlotId() {
        this.value = UUID.randomUUID().toString();
    }

    public SlotId(String value) {
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
    protected boolean equalsCore(SlotId other) {
        return new EqualsBuilder()
                .append(value, other.value)
                .isEquals();
    }
}
