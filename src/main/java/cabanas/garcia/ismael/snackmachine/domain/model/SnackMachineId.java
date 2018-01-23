package cabanas.garcia.ismael.snackmachine.domain.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.util.UUID;

public class SnackMachineId extends ValueObject<SnackMachineId>{

    private final String value;

    public SnackMachineId() {
        this.value = UUID.randomUUID().toString();
    }

    public SnackMachineId(String value) {
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
    protected boolean equalsCore(SnackMachineId other) {
        return new EqualsBuilder()
                .append(value, other.value)
                .isEquals();
    }
}
