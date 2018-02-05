package cabanas.garcia.ismael.dddinpractice.snackmachine.domain.model;

import cabanas.garcia.ismael.dddinpractice.common.AgreggateRoot;
import cabanas.garcia.ismael.dddinpractice.shared.domain.model.Money;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SnackMachine extends AgreggateRoot<SnackMachineId> {

    private static final List<Money> COINS_AND_NOTES = Arrays.asList(
        Money.CENT, Money.TEN_CENT, Money.QUARTER_CENT,
        Money.DOLLAR, Money.FIVE_DOLLAR, Money.TWENTY_DOLLAR);
    private static final short FIRST_POSITION = 1;
    private static final short SECOND_POSITION = 2;
    private static final short THIRD_POSITION = 3;

    private Money moneyInside;
    private double moneyInTransaction;
    private List<Slot> slots;

    public SnackMachine() {
        super(new SnackMachineId());
        this.moneyInside = Money.none();
        this.moneyInTransaction = BigDecimal.ZERO.doubleValue();
        this.slots = new ArrayList<>();
        this.slots.add(new Slot(null, SnackPile.EMPTY, FIRST_POSITION));
        this.slots.add(new Slot(null, SnackPile.EMPTY, SECOND_POSITION));
        this.slots.add(new Slot(null, SnackPile.EMPTY, THIRD_POSITION));
    }

    private SnackMachine(Builder builder) {
        super(builder.id);
        this.moneyInside = new Money(builder.smOneCentCount, builder.smTenCentCount, builder.smQuarterCentCount,
                builder.smOneDollarCount, builder.smFiveDollarCount, builder.smTwentyDollarCount);
        this.slots = new ArrayList<>();
        this.slots.add(builder.slotOne);
        this.slots.add(builder.slotTwo);
        this.slots.add(builder.slotThree);
    }

    public void insertMoney(Money money) {
        if (!COINS_AND_NOTES.contains(money)) {
            throw new BadMoneyException();
        }
        this.moneyInTransaction += money.amount();
        this.moneyInside = this.moneyInside.add(money);
    }

    public void returnMoney() {
        Money returnedMoney = moneyInside.allocate(moneyInTransaction);
        this.moneyInside = moneyInside.substract(returnedMoney);
        this.moneyInTransaction = BigDecimal.ZERO.doubleValue();
    }

    public void buySnack(short position) {
        Slot slot = getSlot(position);

        SnackPile snackPile = slot.snackPile();
        if (snackPile.quantity() == 0) {
            throw new SnackNotFoundException();
        }
        if (this.moneyInTransaction < snackPile.price()) {
            throw new NotEnoughMoneyInsertedException();
        }

        Money change = moneyInside.allocate(moneyInTransaction - snackPile.price());
        this.moneyInside = this.moneyInside.substract(change);
        this.moneyInTransaction = BigDecimal.ZERO.doubleValue();
        slot.dropSnack();

    }

    public double amountInTransaction() {
        return this.moneyInTransaction;
    }

    public double amountInside() {
        return this.moneyInside.amount();
    }

    public void loadSnacks(short position, SnackPile snackPile) {
        Slot slot = new Slot(this, snackPile, position);
        slots.add(position - 1, slot);
    }

    public int snacksOfSlot(int position) {
        return slots.stream()
                .filter(slot -> slot.position() == position)
                .findFirst()
                .orElseThrow(SlotNotFoundException::new)
                .snackPile()
                .quantity();
    }

    public void loadMoney(Money money) {
        this.moneyInside = this.moneyInside.add(money);
    }

    public Money moneyInside() {
        return this.moneyInside;
    }

    public SlotId getSlotId(short position) {
        return getSlot(position)
                .id();
    }

    public SnackPile getSnackPile(short position) {
        return getSlot(position)
                .snackPile();
    }

    private Slot getSlot(short position) {
        return slots.stream()
                .filter(s -> s.position() == position)
                .findFirst()
                .orElseThrow(SlotNotFoundException::new);
    }

    public static Builder builder(SnackMachineId id) {
        return new Builder(id);
    }

    public Money unloadMoney() {
        Money moneyUnloaded = new Money(this.moneyInside);
        this.moneyInside = Money.none();
        return moneyUnloaded;
    }

    public static class Builder {
        private final SnackMachineId id;
        private int smOneCentCount;
        private int smTenCentCount;
        private int smQuarterCentCount;
        private int smOneDollarCount;
        private int smFiveDollarCount;
        private int smTwentyDollarCount;
        private Slot slotOne;
        private Slot slotTwo;
        private Slot slotThree;

        public Builder(SnackMachineId id) {
            this.id = id;
        }

        public Builder setOneCentCount(int oneCentCount) {
            this.smOneCentCount = oneCentCount;
            return this;
        }

        public Builder setTenCentCount(int tenCentCount) {
            this.smTenCentCount = tenCentCount;
            return this;
        }

        public Builder setQuarterCentCount(int quarterCentCount) {
            this.smQuarterCentCount = quarterCentCount;
            return this;
        }

        public Builder setOneDollarCount(int oneDollarCount) {
            this.smOneDollarCount = oneDollarCount;
            return this;
        }

        public Builder setFiveDollarCount(int fiveDollarCount) {
            this.smFiveDollarCount = fiveDollarCount;
            return this;
        }

        public Builder setTwentyDollarCount(int twentyDollarCount) {
            this.smTwentyDollarCount = twentyDollarCount;
            return this;
        }

        public SnackMachine build() {
            return new SnackMachine(this);
        }

        public Builder setSlotOne(Slot slot) {
            this.slotOne = slot;
            return this;
        }

        public Builder setSlotTwo(Slot slot) {
            this.slotTwo = slot;
            return this;
        }

        public Builder setSlotThird(Slot slot) {
            this.slotThree = slot;
            return this;
        }
    }
}
