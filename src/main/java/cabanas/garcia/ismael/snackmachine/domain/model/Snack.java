package cabanas.garcia.ismael.snackmachine.domain.model;

public class Snack extends AgreggateRoot {

    private final String name;

    public Snack(String name) {
        this.name = name;
    }
}
