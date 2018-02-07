package cabanas.garcia.ismael.dddinpractice.snackmachine.domain.model;

public class SnackMachineDto {
    private final double amountInside;
    private final double amountInTransaction;

    public SnackMachineDto(double amountInside, double amountInTransaction) {
        this.amountInside = amountInside;
        this.amountInTransaction = amountInTransaction;
    }

    @Override
    public String toString() {
        return "SnackMachineDto{" +
                "amountInside=" + amountInside +
                ", amountInTransaction=" + amountInTransaction +
                '}';
    }
}
