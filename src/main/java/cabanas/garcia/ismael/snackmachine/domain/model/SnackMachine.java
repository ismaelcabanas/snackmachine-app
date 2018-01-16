package cabanas.garcia.ismael.snackmachine.domain.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SnackMachine extends Entity {

    private static final List<Money> COINS_AND_NOTES = Arrays.asList(
        Money.CENT, Money.TEN_CENT, Money.QUARTER_CENT,
        Money.DOLLAR, Money.FIVE_DOLLAR, Money.TWENTY_DOLLAR);

    private Money moneyInside;
    private Money moneyInTransaction;
    private List<Slot> slots;

    public SnackMachine() {
        this.moneyInside = Money.none();
        this.moneyInTransaction = Money.none();
        this.slots = new ArrayList<>();
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
        this.moneyInside.add(this.moneyInTransaction);
        this.moneyInTransaction = Money.none();
        slots.stream()
                .filter(slot -> slot.position() == position)
                .findFirst()
                .orElseThrow(SlotNotFoundException::new)
                .dropSnack();
    }

    public double amountInTransaction() {
        return this.moneyInTransaction.amount();
    }

    public double amountInside() {
        return this.moneyInside.amount();
    }

    public void addSnacks(short position, Snack snack, int quantity, BigDecimal price) {
        Slot slot = new Slot(snack, this, quantity, price, position);
        slots.add(slot);
    }

    public int snacksOfSlot(int position) {
        return slots.stream()
                .filter(slot -> slot.position() == position)
                .findFirst()
                .orElseThrow(SlotNotFoundException::new)
                .quantity();
    }
}
