package cabanas.garcia.ismael.dddinpractice.common;

public abstract class ValueObject<T> {
    @Override
    public int hashCode() {
        return hashCodeCore();
    }

    protected abstract int hashCodeCore();

    @Override
    public boolean equals(Object obj) {
        T valueObject = (T) obj;

        if (valueObject == null) {
            return false;
        }

        return equalsCore(valueObject);
    }

    protected abstract boolean equalsCore(T other);
}
