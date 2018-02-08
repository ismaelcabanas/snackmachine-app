package cabanas.garcia.ismael.dddinpractice.atm.domain.model;

public class AtmMachineNotFoundException extends RuntimeException {
    public AtmMachineNotFoundException(AtmId atmId) {
        super("ATM Machine " + atmId.getValue() + " not found");
    }
}
