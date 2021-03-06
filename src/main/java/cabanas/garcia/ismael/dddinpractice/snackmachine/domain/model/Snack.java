package cabanas.garcia.ismael.dddinpractice.snackmachine.domain.model;

import cabanas.garcia.ismael.dddinpractice.common.AgreggateRoot;

public class Snack extends AgreggateRoot<SnackId> {

    @SuppressWarnings("PMD")
    private String name;

    public Snack(String name) {
        super(new SnackId());
        this.name = name;
    }

    private Snack(Builder builder) {
        super(builder.id);
    }

    public static Builder builder(SnackId id) {
        return new Builder(id);
    }

    public static class Builder {

        private final SnackId id;

        public Builder(SnackId id) {
            this.id = id;
        }

        public Snack build() {
            return new Snack(this);
        }
    }
}
