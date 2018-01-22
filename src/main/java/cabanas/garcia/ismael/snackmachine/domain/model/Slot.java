package cabanas.garcia.ismael.snackmachine.domain.model;

import java.util.UUID;

public class Slot extends Entity {

    private final SnackMachine snackMachine;
    private final short position;
    private SnackPile snackPile;

    public Slot(SnackMachine snackMachine, SnackPile snackPile, short position) {
        super(UUID.randomUUID().toString());
        this.snackMachine = snackMachine;
        this.snackPile = snackPile;
        this.position = position;
    }

    private Slot(Builder builder) {
        super(builder.id);
        this.snackMachine = builder.snackMachine;
        this.snackPile = builder.snackPile;
        this.position = builder.position;
    }

    public short position() {
        return this.position;
    }

    public void dropSnack() {
        snackPile = snackPile.drop();
    }

    public SnackPile snackPile() {
        return snackPile;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private SnackMachine snackMachine;
        private short position;
        private SnackPile snackPile;
        private String id;

        public Builder setSnackMachine(SnackMachine snackMachine) {
            this.snackMachine = snackMachine;
            return this;
        }

        public Builder setPosition(short position) {
            this.position = position;
            return this;
        }

        public Builder setSnackPile(SnackPile snackPile) {
            this.snackPile = snackPile;
            return this;
        }

        public Slot build() {
            return new Slot(this);
        }

        public Builder setId(String id) {
            this.id = id;
            return this;
        }
    }
}
