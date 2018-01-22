package cabanas.garcia.ismael.snackmachine.domain.model;

import java.util.UUID;

public class Snack extends AgreggateRoot {

    private String name;

    public Snack(String name) {
        super(UUID.randomUUID().toString());
        this.name = name;
    }

    private Snack(Builder builder) {
        super(builder.id);
    }

    public static Builder builder(String id) {
        return new Builder(id);
    }

    public static class Builder {

        private final String id;

        public Builder(String id) {
            this.id = id;
        }

        public Snack build() {
            return new Snack(this);
        }
    }
}
