package cabanas.garcia.ismael.dddinpractice.snackmachine.domain.model;

public class SnackMachineNotFoundException extends RuntimeException {
    public SnackMachineNotFoundException(SnackMachineId snackMachineId) {
        super("SnackMachine " + snackMachineId + " not found");
    }
}
