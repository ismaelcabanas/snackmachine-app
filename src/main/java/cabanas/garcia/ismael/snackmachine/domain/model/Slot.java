package cabanas.garcia.ismael.snackmachine.domain.model;

public class Slot extends Entity {

    private final SnackMachine snackMachine;
    private final short position;
    private SnackPile snackPile;

    public Slot(SnackMachine snackMachine, SnackPile snackPile, short position) {
        this.snackMachine = snackMachine;
        this.snackPile = snackPile;
        this.position = position;
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
}
