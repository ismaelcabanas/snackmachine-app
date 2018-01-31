package cabanas.garcia.ismael.dddinpractice.snackmachine.domain.model;

import cabanas.garcia.ismael.dddinpractice.common.Entity;

public class Slot extends Entity<SlotId> {

    private final SnackMachine snackMachine;
    private final short position;
    private SnackPile snackPile;

    public Slot(SnackMachine snackMachine, SnackPile snackPile, short position) {
        super(new SlotId());
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
        private SlotId id;

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

        public Builder setId(SlotId id) {
            this.id = id;
            return this;
        }
    }
}
