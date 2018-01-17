package cabanas.garcia.ismael.snackmachine.domain.model;

import java.math.BigDecimal;

public class Slot extends Entity {

    private final Snack snack;
    private final SnackMachine snackMachine;
    private int quantity;
    private final BigDecimal price;
    private final short position;

    public Slot(Snack snack, SnackMachine snackMachine, int quantity, BigDecimal price, short position) {
        this.snack = snack;
        this.snackMachine = snackMachine;
        this.quantity = quantity;
        this.price = price;
        this.position = position;
    }

    public short position() {
        return this.position;
    }

    public void dropSnack() {
        quantity --;
    }

    public int quantity() {
        return quantity;
    }

    public double price() {
        return price.doubleValue();
    }
}
