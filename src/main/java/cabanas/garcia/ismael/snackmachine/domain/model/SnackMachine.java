package cabanas.garcia.ismael.snackmachine.domain.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SnackMachine extends AgreggateRoot {

    private static final List<Money> COINS_AND_NOTES = Arrays.asList(
        Money.CENT, Money.TEN_CENT, Money.QUARTER_CENT,
        Money.DOLLAR, Money.FIVE_DOLLAR, Money.TWENTY_DOLLAR);
    private static final short FIRST_POSITION = 1;
    private static final short SECOND_POSITION = 2;
    private static final short THIRD_POSITION = 3;

    private Money moneyInside;
    private Money moneyInTransaction;
    private List<Slot> slots;

    public SnackMachine() {
        this.moneyInside = Money.none();
        this.moneyInTransaction = Money.none();
        this.slots = new ArrayList<>();
        this.slots.add(new Slot(null, SnackPile.EMPTY, FIRST_POSITION));
        this.slots.add(new Slot(null, SnackPile.EMPTY, SECOND_POSITION));
        this.slots.add(new Slot(null, SnackPile.EMPTY, THIRD_POSITION));
    }

    public void insertMoney(Money money) {
        if (!COINS_AND_NOTES.contains(money)) {
            throw new BadMoneyException();
        }
        this.moneyInTransaction.add(money);
    }

    public void returnMoney() {
        this.moneyInTransaction = Money.none();
    }

    public void buySnack(short position) {
        Slot slot = slots.stream()
                .filter(s -> s.position() == position)
                .findFirst()
                .orElseThrow(SlotNotFoundException::new);

        SnackPile snackPile = slot.snackPile();
        if(snackPile.quantity() == 0) {
            throw new SnackNotFoundException();
        }
        if(this.moneyInTransaction.amount() < snackPile.price()) {
            throw new NotEnoughMoneyInsertedException();
        }

        this.moneyInside.add(this.moneyInTransaction);
        this.moneyInTransaction = Money.none();
        slot.dropSnack();
    }

    public double amountInTransaction() {
        return this.moneyInTransaction.amount();
    }

    public double amountInside() {
        return this.moneyInside.amount();
    }

    public void addSnacks(short position, Snack snack, int quantity, BigDecimal price) {
        Slot slot = new Slot(this, new SnackPile(snack, quantity, price), position);
        slots.add(position-1, slot);
    }

    public int snacksOfSlot(int position) {
        return slots.stream()
                .filter(slot -> slot.position() == position)
                .findFirst()
                .orElseThrow(SlotNotFoundException::new)
                .snackPile()
                .quantity();
    }
}
