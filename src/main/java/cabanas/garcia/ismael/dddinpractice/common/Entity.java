package cabanas.garcia.ismael.dddinpractice.common;

public abstract class Entity<ID> {
    private ID id;

    public ID id() {
        return id;
    }

    protected Entity(ID id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Entity entity = (Entity) o;

        return new org.apache.commons.lang3.builder.EqualsBuilder()
                .append(id, entity.id)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new org.apache.commons.lang3.builder.HashCodeBuilder(17, 37)
                .append(id)
                .toHashCode();
    }
}
