package cabanas.garcia.ismael.dddinpractice.management.domain.model;

public class HeadOfficeNotFoundException extends RuntimeException {
    public HeadOfficeNotFoundException(HeadOfficeId headOfficeId) {
        super("Head Office " + headOfficeId.getValue() + " not found");
    }
}
